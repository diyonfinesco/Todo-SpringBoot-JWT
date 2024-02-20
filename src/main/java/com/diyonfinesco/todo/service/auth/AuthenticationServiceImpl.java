package com.diyonfinesco.todo.service.auth;

import com.diyonfinesco.todo.config.JwtService;
import com.diyonfinesco.todo.dto.auth.AuthResponseDTO;
import com.diyonfinesco.todo.dto.user.LoginUserDTO;
import com.diyonfinesco.todo.dto.user.RegisterUserDTO;
import com.diyonfinesco.todo.mapper.user.RegisterUserMapper;
import com.diyonfinesco.todo.model.entity.UserEntity;
import com.diyonfinesco.todo.model.enums.Role;
import com.diyonfinesco.todo.repository.UserRepository;
import com.diyonfinesco.todo.util.CustomResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RegisterUserMapper registerUserMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public CustomResponseEntity register(RegisterUserDTO registerUserDTO) {
        UserEntity user = userRepository.findByEmail(registerUserDTO.getEmail()).orElse(null);

        if (user != null) {
            return new CustomResponseEntity(HttpStatus.BAD_REQUEST.value(), false, "User already exist!");
        }

        UserEntity newUser = registerUserMapper.toEntity(registerUserDTO);
        newUser.setRole(Role.ROLE_USER);
        newUser.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
        newUser.setEmail(registerUserDTO.getEmail().toLowerCase());

        userRepository.save(newUser);

        String jwtToken = jwtService.generateToken(newUser);

        AuthResponseDTO authResponseDTO = new AuthResponseDTO(newUser.getEmail(), jwtToken);

        return new CustomResponseEntity(HttpStatus.CREATED.value(), true, authResponseDTO);
    }

    @Override
    public CustomResponseEntity authenticate(LoginUserDTO loginUserDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserDTO.getEmail(), loginUserDTO.getPassword()));

        UserEntity user = userRepository.findByEmail(loginUserDTO.getEmail()).orElse(null);

        if (user == null) {
            return new CustomResponseEntity(HttpStatus.NOT_FOUND.value(), false, "User not found");
        }

        String jwtToken = jwtService.generateToken(user);

        AuthResponseDTO authResponseDTO = new AuthResponseDTO(user.getEmail(), jwtToken);

        return new CustomResponseEntity(HttpStatus.OK.value(), true, authResponseDTO);
    }
}
