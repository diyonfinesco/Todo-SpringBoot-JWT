package com.diyonfinesco.todo.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomResponseEntity {
    private int status;
    private boolean success;
    private Object result;
}
