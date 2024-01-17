package com.diyonfinesco.todo.controller;

import com.diyonfinesco.todo.dto.user.RegisterUserDTO;
import com.diyonfinesco.todo.service.user.UserService;
import com.diyonfinesco.todo.util.CustomResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping()
    public CustomResponseEntity create(@Valid @RequestBody RegisterUserDTO registerUserDTO) {
        return userService.create(registerUserDTO);
    }

    @GetMapping()
    public CustomResponseEntity findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public CustomResponseEntity findById(@PathVariable String id) {
        return userService.findById(id);
    }
}
