package com.diyonfinesco.todo.repository;

import com.diyonfinesco.todo.model.entity.UserEntity;
import com.diyonfinesco.todo.model.enums.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @BeforeAll
    static void beforeAll() {

    }

    @Nested
    class findByEmail {
        @Test
        void itShouldReturnAUserWithExistEmail() {
            String email = "john@example.com";

            // arrange
            UserEntity user = new UserEntity();

            user.setEmail(email);
            user.setPassword("password");
            user.setRole(Role.ROLE_USER);

            userRepository.save(user);

            // act
            UserEntity userExistsByEmail = userRepository.findByEmail(email).orElse(null);

            // assert
            assertThat(userExistsByEmail).isNotNull();
            assertThat(userExistsByEmail.getEmail()).isEqualTo(email);
        }

        @Test
        void itShouldReturnNullForNonExistEmail() {
            String email = "john@example.com";

            // act
            UserEntity userExistsByEmail = userRepository.findByEmail(email).orElse(null);

            // assert
            assertThat(userExistsByEmail).isNull();
        }
    }
}