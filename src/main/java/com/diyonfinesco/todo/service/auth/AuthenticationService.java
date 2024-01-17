package com.diyonfinesco.todo.service.auth;

import com.diyonfinesco.todo.dto.user.LoginUserDTO;
import com.diyonfinesco.todo.dto.user.RegisterUserDTO;
import com.diyonfinesco.todo.util.CustomResponseEntity;

public interface AuthenticationService {
    CustomResponseEntity register(RegisterUserDTO registerUserDTO);

    CustomResponseEntity authenticate(LoginUserDTO loginUserDTO);
}
