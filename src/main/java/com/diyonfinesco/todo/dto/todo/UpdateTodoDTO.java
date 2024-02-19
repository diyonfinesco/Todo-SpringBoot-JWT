package com.diyonfinesco.todo.dto.todo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UpdateTodoDTO {
    @NotBlank(message = "Id is required")
    @Size(min = 24, max = 24)
    private String id;

    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 10, message = "The length of the title must be between 2 and 10 characters.")
    private String title;

    @NotNull(message = "Completed is required!")
    @Pattern(regexp = "^true$|^false$", message = "allowed input: true or false")
    private String completed;

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title.trim();
    }

    public void setCompleted(String completed) {
        this.completed = completed.trim();
    }
}
