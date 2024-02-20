package com.diyonfinesco.todo.service.user;

import com.diyonfinesco.todo.dto.user.RegisterUserDTO;
import com.diyonfinesco.todo.mapper.user.DefaultUserMapper;
import com.diyonfinesco.todo.mapper.user.RegisterUserMapper;
import com.diyonfinesco.todo.model.entity.UserEntity;
import com.diyonfinesco.todo.model.enums.Role;
import com.diyonfinesco.todo.repository.UserRepository;
import com.diyonfinesco.todo.util.CustomResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RegisterUserMapper registerUserMapper;

    @Autowired
    DefaultUserMapper defaultUserMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public CustomResponseEntity create(RegisterUserDTO registerUserDTO) {
        UserEntity user = userRepository.findByEmail(registerUserDTO.getEmail()).orElse(null);

        if (user != null) {
            return new CustomResponseEntity(HttpStatus.BAD_REQUEST.value(), false, "User already exist!");
        }

        UserEntity newUser = registerUserMapper.toEntity(registerUserDTO);
        newUser.setRole(Role.USER);
        newUser.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));

        userRepository.save(newUser);
        return new CustomResponseEntity(HttpStatus.CREATED.value(), true, defaultUserMapper.toDTO(newUser));
    }

    @Override
    public CustomResponseEntity findAll() {
        List<UserEntity> users = userRepository.findAll();

        return new CustomResponseEntity(HttpStatus.OK.value(), true, defaultUserMapper.toDTOList(users));
    }

    @Override
    public CustomResponseEntity findById(String id) {
        UserEntity user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return new CustomResponseEntity(HttpStatus.NOT_FOUND.value(), false, "User not found!");
        }

        return new CustomResponseEntity(HttpStatus.OK.value(), true, defaultUserMapper.toDTO(user));
    }

    @Override
    public CustomResponseEntity findByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return new CustomResponseEntity(HttpStatus.NOT_FOUND.value(), false, "User not found!");
        }

        return new CustomResponseEntity(HttpStatus.OK.value(), true, defaultUserMapper.toDTO(user));
    }

    @Override
    public UserEntity getLoggedInUser(){
        UserEntity userFromSecurity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserEntity currentUser = new UserEntity();

        currentUser.setId(userFromSecurity.getId());
        currentUser.setEmail(userFromSecurity.getEmail());
        currentUser.setRole(userFromSecurity.getRole());

        return currentUser;
    }
}
