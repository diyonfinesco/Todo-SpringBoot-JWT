package com.diyonfinesco.todo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "todos")
public class TodoEntity {
    @Id
    private String id;

    @Indexed(unique = true)
    private String title;

    private Boolean completed;

}
