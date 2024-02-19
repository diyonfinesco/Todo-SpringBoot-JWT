package com.diyonfinesco.todo.dto.todo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateTodoDTO {
    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 10, message = "The length of the title must be between 2 and 10 characters.")
    private String title;

    public void setTitle(String title) {
        this.title = title.trim();
    }
}
