package com.varorest.varorest.user.services;

import com.varorest.varorest.user.model.User;
import com.varorest.varorest.user.model.UserKills;
import com.varorest.varorest.user.repositories.UserKillsRepository;
import com.varorest.varorest.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserKillsRepository userKillsRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserKillsRepository userKillsRepository) {
        this.userRepository = userRepository;
        this.userKillsRepository = userKillsRepository;
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
}
