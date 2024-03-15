package com.diyonfinesco.todo.controller;

import com.diyonfinesco.todo.dto.user.LoginUserDTO;
import com.diyonfinesco.todo.dto.user.RegisterUserDTO;
import com.diyonfinesco.todo.service.auth.AuthenticationService;
import com.diyonfinesco.todo.util.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CustomResponse> register(@Valid @RequestBody RegisterUserDTO registerUserDTO) {
        CustomResponse response = authenticationService.register(registerUserDTO);
        return new ResponseEntity<>(response, response.getHttpStatusCode());
    }


    @PostMapping("/login")
    public ResponseEntity<CustomResponse> login(@Valid @RequestBody LoginUserDTO loginUserDTO) {
        CustomResponse response = authenticationService.authenticate(loginUserDTO);
        return new ResponseEntity<>(response, response.getHttpStatusCode());
    }
}