package com.diyonfinesco.todo.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

@NoArgsConstructor
@Data
public class CustomResponse {

    private int status;

    private boolean success;

    private Object result;

    @JsonIgnore
    private HttpStatusCode httpStatusCode;

    public CustomResponse(HttpStatusCode status, boolean success, Object result) {
        this.status = status.value();
        this.success = success;
        this.result = result;
        this.httpStatusCode = status;
    }
}
