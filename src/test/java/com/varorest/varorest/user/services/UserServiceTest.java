package com.varorest.varorest.user.services;

import com.varorest.varorest.user.dto.LocationDto;
import com.varorest.varorest.user.model.User;
import com.varorest.varorest.user.model.UserKills;
import com.varorest.varorest.user.model.UserTeam;
import com.varorest.varorest.user.repositories.UserKillsRepository;
import com.varorest.varorest.user.repositories.UserRepository;
import com.varorest.varorest.user.repositories.UserTeamRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    private UserRepository userRepository;
    private UserKillsRepository userKillsRepository;
    private UserTeamRepository userTeamRepository;

    private UserService userService;

    @Before
    public void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userKillsRepository = Mockito.mock(UserKillsRepository.class);
        userTeamRepository = Mockito.mock(UserTeamRepository.class);

        userService = new UserService(userRepository, userKillsRepository, userTeamRepository);
    }

    @Test
    @DisplayName("Tests whether the player is alive")
    public void isAliveTrue() {
        // given
        User expected = User.builder().uuid("fcd7247d-7c72-4dab-a1bc-af73d0dda87c")
                            .alive(true)
                            .build();

        // when
        Mockito.when(userRepository.findUserByUuid(expected.getUuid())).thenReturn(expected);

        // then
        assertTrue(userService.isAlive(expected.getUuid()).orElseThrow(() -> new EntityNotFoundException("Uesr not found")));
    }

    @Test
    @DisplayName("Test whether the player is not alive")
    public void isAliveFalse() {
        // given
        User expected = User.builder().uuid("fcd7247d-7c72-4dab-a1bc-af73d0dda87c")
                            .alive(false)
                            .build();

        // when
        Mockito.when(userRepository.findUserByUuid(expected.getUuid())).thenReturn(expected);

        // then
        assertFalse(userService.isAlive(expected.getUuid()).orElseThrow(() -> new EntityNotFoundException("Uesr not found")));
    }

    @Test
    @DisplayName("Test whether the coordinates are correct")
    public void coordinates() {
        // given
        User expected = User.builder().uuid("fcd7247d-7c72-4dab-a1bc-af73d0dda87c")
                            .x(40)
                            .y(40)
                            .z(40)
                            .build();

        // when
        Mockito.when(userRepository.findUserByUuid(expected.getUuid())).thenReturn(expected);

        // then
        assertEquals(userService.getPlayerLocation(expected.getUuid()).orElseThrow
                (() -> new EntityNotFoundException("Uesr not found")), new LocationDto(expected.getX(), expected.getY(), expected.getZ()));
    }

    @Test
    @DisplayName("Test whether all Teams are correctly retrieved")
    public void teams() {
        // given
        User user1 = User.builder().uuid("fcd7247d-7c72-4dab-a1bc-af73d0dda87c").build();
        User user2 = User.builder().uuid("fcd7247d-7c72-4dab-a1bc-af73d0dda87c").build();

        UserTeam userTeam = UserTeam.builder().member1(user1).member2(user2).build();
        List<UserTeam> expected = List.of(userTeam);

        // when
        Mockito.when(userTeamRepository.findAll()).thenReturn(expected);

        // then
        List<UserTeam> actual = userService.getAllTeams().orElseThrow(() -> new EntityNotFoundException("Teams not found!"));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Tests whether the correct amount of kills is being returned")
    public void getKills() {
        // given
        int expected = 4;
        User user = User.builder().uuid("fcd7247d-7c72-4dab-a1bc-af73d0dda87c").build();

        User user1 = User.builder().uuid("fcd7247d-7c72-4dab-a1bc-af73d0dda87c").build();
        User user2 = User.builder().uuid("fcd7247d-7c72-4dab-a1bc-af73d0dda87c").build();
        User user3 = User.builder().uuid("fcd7247d-7c72-4dab-a1bc-af73d0dda87c").build();
        User user4 = User.builder().uuid("fcd7247d-7c72-4dab-a1bc-af73d0dda87c").build();

        UserKills kill1 = UserKills.builder().killer(user).killed(user1).build();
        UserKills kill2 = UserKills.builder().killer(user).killed(user2).build();
        UserKills kill3 = UserKills.builder().killer(user).killed(user3).build();
        UserKills kill4 = UserKills.builder().killer(user).killed(user4).build();

        List<UserKills> kills = List.of(kill1, kill2, kill3, kill4);

        // when
        Mockito.when(userKillsRepository.findUserKillsByKiller_Uuid(user.getUuid())).thenReturn(kills);

        // then
        assertEquals(expected, userService.getKills(user.getUuid()).orElseThrow(() -> new EntityNotFoundException("Not found Kills")));
    }

}