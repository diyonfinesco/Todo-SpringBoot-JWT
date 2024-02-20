package com.diyonfinesco.todo.dto.todo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ReturnTodoDTO {
    private String id;

    private String title;

    private boolean completed;
}
