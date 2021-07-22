package com.varorest.varorest.user.services;

import com.varorest.varorest.user.dto.LocationDto;
import com.varorest.varorest.user.model.User;
import com.varorest.varorest.user.model.UserKills;
import com.varorest.varorest.user.model.UserTeam;
import com.varorest.varorest.user.repositories.UserKillsRepository;
import com.varorest.varorest.user.repositories.UserRepository;
import com.varorest.varorest.user.repositories.UserTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Integer> getKills(User user) {
        List<UserKills> rows = userKillsRepository.findUserKillsByKiller(user);

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

}
