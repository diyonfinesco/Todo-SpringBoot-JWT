package com.diyonfinesco.todo.service.user;

import com.diyonfinesco.todo.dto.user.RegisterUserDTO;
import com.diyonfinesco.todo.mapper.user.RegisterUserMapper;
import com.diyonfinesco.todo.mapper.user.ReturnUserMapper;
import com.diyonfinesco.todo.model.entity.UserEntity;
import com.diyonfinesco.todo.model.enums.Role;
import com.diyonfinesco.todo.repository.UserRepository;
import com.diyonfinesco.todo.util.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    private RegisterUserMapper registerUserMapper;

    private ReturnUserMapper returnUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ReturnUserMapper returnUserMapper) {
        this.userRepository = userRepository;
        this.returnUserMapper = returnUserMapper;
    }

    @Override
    public CustomResponse create(RegisterUserDTO registerUserDTO) {
        UserEntity user = userRepository.findByEmail(registerUserDTO.getEmail()).orElse(null);

        if (user != null) {
            return new CustomResponse(HttpStatus.BAD_REQUEST, false, "User already exist!");
        }

        UserEntity newUser = registerUserMapper.toEntity(registerUserDTO);
        newUser.setRole(Role.ROLE_USER);
        newUser.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));

        userRepository.save(newUser);
        return new CustomResponse(HttpStatus.CREATED, true, returnUserMapper.toDTO(newUser));
    }

    @Override
    public CustomResponse findAll() {
        List<UserEntity> users = userRepository.findAll();
        System.out.println(returnUserMapper.toDTOList(users));
        return new CustomResponse(HttpStatus.OK, true, returnUserMapper.toDTOList(users));
    }

    @Override
    public CustomResponse findById(String id) {

        UserEntity user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return new CustomResponse(HttpStatus.NOT_FOUND, false, "User not found!");
        }

        return new CustomResponse(HttpStatus.OK, true, returnUserMapper.toDTO(user));
    }

    @Override
    public CustomResponse findByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return new CustomResponse(HttpStatus.NOT_FOUND, false, "User not found!");
        }

        return new CustomResponse(HttpStatus.OK, true, returnUserMapper.toDTO(user));
    }

    @Override
    public UserEntity getLoggedInUser() {
        UserEntity userFromSecurity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserEntity currentUser = new UserEntity();

        currentUser.setId(userFromSecurity.getId());
        currentUser.setEmail(userFromSecurity.getEmail());
        currentUser.setRole(userFromSecurity.getRole());

        return currentUser;
    }
}
