package com.diyonfinesco.todo.service.user;

import com.diyonfinesco.todo.mapper.user.ReturnUserMapper;
import com.diyonfinesco.todo.repository.UserRepository;
import de.flapdoodle.embed.mongo.spring.autoconfigure.EmbeddedMongoAutoConfiguration;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.mockito.Mockito.verify;

@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Spy
    private ReturnUserMapper returnUserMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @Disabled
    void create() {
    }

    @Test
    void findAll() {
        userService.findAll();

        verify(userRepository).findAll();
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