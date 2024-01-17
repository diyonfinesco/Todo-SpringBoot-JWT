package com.diyonfinesco.todo.controller;

import com.diyonfinesco.todo.dto.user.LoginUserDTO;
import com.diyonfinesco.todo.dto.user.RegisterUserDTO;
import com.diyonfinesco.todo.service.auth.AuthenticationService;
import com.diyonfinesco.todo.util.CustomResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/register")
    public CustomResponseEntity register(@Valid @RequestBody RegisterUserDTO registerUserDTO) {
        return authenticationService.register(registerUserDTO);
    }


    @PostMapping("/login")
    public CustomResponseEntity login(@Valid @RequestBody LoginUserDTO loginUserDTO) {
        return authenticationService.authenticate(loginUserDTO);
    }
}
