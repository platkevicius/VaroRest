package com.varorest.varorest.user.repositories;

import com.varorest.varorest.user.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindByUUID_thenReturnUser() {
        // Writing into database expected user (given)
        User expected = User.builder().uuid("fcd7247d-7c72-4dab-a1bc-af73d0dda87c")
                    .alive(true)
                    .online(false)
                    .x(40)
                    .y(40)
                    .z(40)
                    .lastLogging(LocalDateTime.of(1, 1, 1,2, 2))
                    .build();
        entityManager.persist(expected);
        entityManager.flush();

        // retrieving from database user (when)
        User actual = userRepository.findUserByUuid(expected.getUuid());

        // Checking whether they are equals (then)
        assertEquals(expected, actual);
    }

}