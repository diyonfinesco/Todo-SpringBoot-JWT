package com.diyonfinesco.todo.exception;

import com.diyonfinesco.todo.util.CustomResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponse> handleSecurityException(Exception exception) {
        CustomResponse errorResponse = null;

//        exception.printStackTrace();

        if (exception instanceof BadCredentialsException) {
            errorResponse = new CustomResponse(HttpStatus.UNAUTHORIZED, false, "The username or password is incorrect");

            return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(errorResponse.getStatus()));
        }

        if (exception instanceof AccountStatusException) {
            errorResponse = new CustomResponse(HttpStatus.FORBIDDEN, false, "The account is locked");
        }

        if (exception instanceof AccessDeniedException) {
            errorResponse = new CustomResponse(HttpStatus.FORBIDDEN, false, "You are not authorized to access this resource");
        }

        if (exception instanceof SignatureException) {
            errorResponse = new CustomResponse(HttpStatus.FORBIDDEN, false, "The JWT signature is invalid");
        }

        if (exception instanceof ExpiredJwtException) {
            errorResponse = new CustomResponse(HttpStatus.FORBIDDEN, false, "The JWT token has expired");
        }

        if (errorResponse == null) {
            errorResponse = new CustomResponse(HttpStatus.INTERNAL_SERVER_ERROR, false, "Unknown internal server error.");
        }

        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(errorResponse.getStatus()));
    }
}
