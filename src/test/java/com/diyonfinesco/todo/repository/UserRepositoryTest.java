package com.diyonfinesco.todo.repository;

import com.diyonfinesco.todo.model.entity.UserEntity;
import com.diyonfinesco.todo.model.enums.Role;
import de.flapdoodle.embed.mongo.spring.autoconfigure.EmbeddedMongoAutoConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    static void beforeAll() {

    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Nested
    class findByEmail {
        @Test
        void shouldReturnAUserWithExistEmail() {
            // arrange
            String email = "john@example.com";
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
        void shouldReturnNullForNonExistEmail() {
            // act
            UserEntity userExistsByEmail = userRepository.findByEmail("john@example.com").orElse(null);

            // assert
            assertThat(userExistsByEmail).isNull();
        }
    }
}