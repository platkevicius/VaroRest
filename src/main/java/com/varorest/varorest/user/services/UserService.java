package com.varorest.varorest.user.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.varorest.varorest.user.dto.LocationDto;
import com.varorest.varorest.user.dto.UserStat;
import com.varorest.varorest.user.model.User;
import com.varorest.varorest.user.model.UserKills;
import com.varorest.varorest.user.model.UserTeam;
import com.varorest.varorest.user.repositories.UserKillsRepository;
import com.varorest.varorest.user.repositories.UserRepository;
import com.varorest.varorest.user.repositories.UserTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserKillsRepository userKillsRepository;
    private final UserTeamRepository userTeamRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserKillsRepository userKillsRepository,
                       UserTeamRepository userTeamRepository) {
        this.userRepository = userRepository;
        this.userKillsRepository = userKillsRepository;
        this.userTeamRepository = userTeamRepository;
    }

    public Optional<Boolean> isAlive(String uuid) {
        User user = userRepository.findUserByUuid(uuid);

        if (user == null)
            return Optional.empty();

        return Optional.of(user.isAlive());
    }

    public List<UserStat> getUserStats() {
        List<User> allUsers = userRepository.findAll();
        List<UserStat> stats = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        String preUrl = "https://api.mojang.com/user/profiles/";
        String afterUrl = "/names";
        String url;

        ObjectMapper objectMapper = new ObjectMapper();
        for (User user : allUsers) {
            Optional<Boolean> alive = isAlive(user.getUuid());
            Optional<Integer> kills = getKills(user.getUuid());

            if (alive.isEmpty() || kills.isEmpty()) {continue;}

            url = preUrl + user.getUuid() + afterUrl;
            String userName = "";

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            try {
                JsonNode root = objectMapper.readTree(response.getBody());
                userName = root.get(root.size()-1).get("name").asText();
            }
            catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            stats.add(UserStat.builder()
                      .name(userName)
                      .kills(kills.get())
                      .alive(alive.get())
                      .build());
        }

        return stats;
    }

    public Optional<Integer> getKills(String uuid) {
        List<UserKills> rows = userKillsRepository.findUserKillsByKiller_Uuid(uuid);

        return Optional.of(rows.size());
    }

    public Optional<LocationDto> getPlayerLocation(String uuid) {
        User user = userRepository.findUserByUuid(uuid);

        if (user == null)
            return Optional.empty();

        return Optional.of(new LocationDto(user.getX(), user.getY(), user.getZ()));
    }

    public Optional<List<UserTeam>> getAllTeams() {
        List<UserTeam> teams = userTeamRepository.findAll();

        if (teams.isEmpty())
            return Optional.empty();

        return Optional.of(teams);
    }

    public List<String> getAllUsers() {
        return userRepository.findAll().stream().map(User::getUuid).collect(Collectors.toList());
    }

    public List<User> getLocations() {
        return userRepository.findAll()
                .stream()
                .filter((user) -> {
                    LocalDateTime lastLogging = user.getLastLogging().plusDays(4L);

                    return lastLogging.isAfter(LocalDateTime.now());
                })
                .collect(Collectors.toList());
    }
}
