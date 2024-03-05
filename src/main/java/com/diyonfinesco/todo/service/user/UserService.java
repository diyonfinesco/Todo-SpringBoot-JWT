package com.diyonfinesco.todo.service.user;

import com.diyonfinesco.todo.dto.user.RegisterUserDTO;
import com.diyonfinesco.todo.model.entity.UserEntity;
import com.diyonfinesco.todo.util.CustomResponse;

public interface UserService {
    CustomResponse create(RegisterUserDTO registerUserDTO);

    CustomResponse findAll();

    CustomResponse findById(String id);

    CustomResponse findByEmail(String email);

    UserEntity getLoggedInUser();
}
