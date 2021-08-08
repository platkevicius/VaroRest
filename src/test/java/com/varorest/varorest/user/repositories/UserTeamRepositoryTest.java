package com.varorest.varorest.user.repositories;

import com.varorest.varorest.user.model.User;
import com.varorest.varorest.user.model.UserTeam;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserTeamRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserTeamRepository userTeamRepository;

    @Test
    @DisplayName("Check whether the users are returned correctly")
    public void returnUserTeamsCorrectly() {
        // given
        User member_1 = User.builder().uuid("test").build();
        User member_2 = User.builder().uuid("test2").build();

        UserTeam userTeam = UserTeam.builder().member1(member_1).member2(member_2).build();
        testEntityManager.persist(userTeam);
        testEntityManager.flush();

        // when
        List<UserTeam> actual = userTeamRepository.findAll();

        // then
        assertEquals(userTeam, actual.get(0));
    }

}