package com.diyonfinesco.todo.controller;

import com.diyonfinesco.todo.dto.user.RegisterUserDTO;
import com.diyonfinesco.todo.service.user.UserService;
import com.diyonfinesco.todo.util.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping()
    public ResponseEntity<CustomResponse> create(@Valid @RequestBody RegisterUserDTO registerUserDTO) {
        CustomResponse response = userService.create(registerUserDTO);
        return new ResponseEntity<>(response, response.getHttpStatusCode());
    }

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CustomResponse> findAll() {
        CustomResponse response = userService.findAll();
        return new ResponseEntity<>(response, response.getHttpStatusCode());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CustomResponse> findById(@PathVariable String id) {
        CustomResponse response = userService.findById(id);
        return new ResponseEntity<>(response, response.getHttpStatusCode());
    }
}