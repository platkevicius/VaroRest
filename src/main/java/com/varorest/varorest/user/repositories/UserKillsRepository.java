package com.varorest.varorest.user.repositories;

import com.varorest.varorest.user.model.User;
import com.varorest.varorest.user.model.UserKills;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserKillsRepository extends JpaRepository<UserKills, Long> {

    List<UserKills> findUserKillsByKiller(User killer);

}
