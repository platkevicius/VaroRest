package com.varorest.varorest.user.repositories;

import com.varorest.varorest.user.model.User;
import com.varorest.varorest.user.model.UserKills;
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
public class UserKillsRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserKillsRepository userKillsRepository;

    @Test
    @DisplayName("Test whether the repository retrieves the data correctly")
    public void returnKillsCorrectly() {
        // given
        User killer = User.builder().uuid("fcd7247d-7c72-4dab-a1bc-af73d0dda87c").build();

        User killed1 = User.builder().uuid("fcd7247d-7c72-4dab-a1bc-af73d0dda87d").build();
        User killed2 = User.builder().uuid("fcd7247d-7c72-4dab-a1bc-af73d0dda87e").build();

        UserKills userKills1 = UserKills.builder().killer(killer).killed(killed1).build();
        UserKills userKills2 = UserKills.builder().killer(killer).killed(killed2).build();

        testEntityManager.persist(userKills1);
        testEntityManager.persist(userKills2);
        testEntityManager.flush();

        // when
        List<UserKills> userKills = userKillsRepository.findUserKillsByKiller_Uuid(killer.getUuid());

        // then
        assertEquals(2, userKills.size());
    }

}