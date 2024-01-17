package com.diyonfinesco.todo.repository;

import com.diyonfinesco.todo.model.entity.TodoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoRepository extends MongoRepository<TodoEntity, String> {
    TodoEntity findByTitle(String title);

    TodoEntity findByTitleAndIdNot(String title, String id);
}
