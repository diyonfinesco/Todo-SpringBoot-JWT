package com.diyonfinesco.todo.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RegisterUserDTO {
    @NotBlank(message = "Email is required!")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Please enter a valid email")
    private String email;

    @NotBlank(message = "Password is required!")
    @Size(min = 8, message = "Password should contain at least 8 characters!")
    private String password;
}
