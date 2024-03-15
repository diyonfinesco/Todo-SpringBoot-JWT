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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

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
        List<UserEntity> users = List.of(
                    new UserEntity("1", "john@example.com", "password", Role.ROLE_USER),
                    new UserEntity("2", "smith@example.com", "password", Role.ROLE_ADMIN)
        );

        when(userRepository.findAll()).thenReturn(users);

        // act
        CustomResponse response = userService.findAll();

        // assert
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.isSuccess()).isTrue();
        System.out.println(response.getResult()!=null && response.getResult().getClass().isArray());
        assertThat(response.getResult()).usingRecursiveComparison().isEqualTo(returnUserMapper.toDTOList(users));
    }

    @Test
    @Disabled
    void findById() {
    }

    @Test
    @Disabled
    void findByEmail() {
    }

    @Test
    @Disabled
    void getLoggedInUser() {
    }
}