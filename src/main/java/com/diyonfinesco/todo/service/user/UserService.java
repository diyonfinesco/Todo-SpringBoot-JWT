package com.diyonfinesco.todo.service.user;

import com.diyonfinesco.todo.dto.user.RegisterUserDTO;
import com.diyonfinesco.todo.util.CustomResponseEntity;

public interface UserService {
    CustomResponseEntity create(RegisterUserDTO registerUserDTO);

    CustomResponseEntity findAll();

    CustomResponseEntity findById(String id);

    CustomResponseEntity findByEmail(String email);
}
