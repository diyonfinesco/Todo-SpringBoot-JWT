package com.diyonfinesco.todo.service.user;

import com.diyonfinesco.todo.dto.user.ReturnUserDTO;
import com.diyonfinesco.todo.mapper.user.ReturnUserMapper;
import com.diyonfinesco.todo.model.entity.UserEntity;
import com.diyonfinesco.todo.model.enums.Role;
import com.diyonfinesco.todo.repository.UserRepository;
import com.diyonfinesco.todo.util.CustomResponse;
import de.flapdoodle.embed.mongo.spring.autoconfigure.EmbeddedMongoAutoConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ReturnUserMapper returnUserMapper;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository, returnUserMapper);
    }

    @Test
    @Disabled
    void create() {
    }

    @Test
    void findAll() {
        // arrange
        List<UserEntity> usersEntityList = Arrays.asList(
                new UserEntity("1", "john@example.com", "password", Role.ROLE_USER),
                new UserEntity("2", "smith@example.com", "password", Role.ROLE_ADMIN)
        );

        List<ReturnUserDTO> usersDTOList = Arrays.asList(
                new ReturnUserDTO("1", "john@example.com"),
                new ReturnUserDTO("2", "smith@example.com")
        );

        when(userRepository.findAll()).thenReturn(usersEntityList);
        when(returnUserMapper.toDTOList(any())).thenReturn(usersDTOList);

        // act
        CustomResponse response = userService.findAll();

        // assert
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getResult()).usingRecursiveComparison().isEqualTo(usersDTOList);
    }

    @Nested
    class findById {

        @Test
        void shouldReturnResponseWithUser() {
            // arrange
            UserEntity userEntity = new UserEntity("1", "john@example.com", "password", Role.ROLE_USER);
            ReturnUserDTO userDTO = new ReturnUserDTO("1", "john@example.com");
            when(userRepository.findById("65f419007e3ed7228a35a8cd")).thenReturn(Optional.of(userEntity));
            when(returnUserMapper.toDTO(userEntity)).thenReturn(userDTO);

            // act
            CustomResponse response = userService.findById("65f419007e3ed7228a35a8cd");

            // assert
            assertThat(response.getStatus()).isEqualTo(200);
            assertThat(response.isSuccess()).isTrue();
            assertThat(response.getResult()).isEqualTo(userDTO);
        }

        @Test
        void shouldReturnResponseWithNotFound() {
            // act
            CustomResponse response = userService.findById("65f419007e3ed7228a35a8cd");

            // assert
            assertThat(response.getStatus()).isEqualTo(404);
            assertThat(response.isSuccess()).isFalse();
            assertThat(response.getResult()).isEqualTo("User not found!");
        }
    }

    @Nested
    class findByEmail {

        @Test
        void shouldReturnResponseWithUser() {
            // arrange
            String email = "john@example.com";
            UserEntity userEntity = new UserEntity("1", email, "password", Role.ROLE_USER);
            ReturnUserDTO userDTO = new ReturnUserDTO("1", "john@example.com");
            when(userRepository.findByEmail(email)).thenReturn(Optional.of(userEntity));
            when(returnUserMapper.toDTO(userEntity)).thenReturn(userDTO);

            // act
            CustomResponse response = userService.findByEmail(email);

            // assert
            assertThat(response.getStatus()).isEqualTo(200);
            assertThat(response.isSuccess()).isTrue();
            assertThat(response.getResult()).isEqualTo(userDTO);
        }

        @Test
        void shouldReturnResponseWithNotFound() {
            // act
            CustomResponse response = userService.findByEmail("john@example.com");

            // assert
            assertThat(response.getStatus()).isEqualTo(404);
            assertThat(response.isSuccess()).isFalse();
            assertThat(response.getResult()).isEqualTo("User not found!");
        }
    }

    @Test
    @Disabled
    void getLoggedInUser() {
    }
}