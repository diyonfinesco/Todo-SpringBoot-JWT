package com.diyonfinesco.todo.controller;

import com.diyonfinesco.todo.dto.user.RegisterUserDTO;
import com.diyonfinesco.todo.service.user.UserService;
import com.diyonfinesco.todo.util.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping()
    public CustomResponse create(@Valid @RequestBody RegisterUserDTO registerUserDTO) {
        return userService.create(registerUserDTO);
    }

    @GetMapping()
    public CustomResponse findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public CustomResponse findById(@PathVariable String id) {
        return userService.findById(id);
    }
}
