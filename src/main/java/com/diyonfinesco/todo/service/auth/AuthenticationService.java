package com.diyonfinesco.todo.service.auth;

import com.diyonfinesco.todo.dto.user.LoginUserDTO;
import com.diyonfinesco.todo.dto.user.RegisterUserDTO;
import com.diyonfinesco.todo.util.CustomResponse;

public interface AuthenticationService {
    CustomResponse register(RegisterUserDTO registerUserDTO);

    CustomResponse authenticate(LoginUserDTO loginUserDTO);
}
