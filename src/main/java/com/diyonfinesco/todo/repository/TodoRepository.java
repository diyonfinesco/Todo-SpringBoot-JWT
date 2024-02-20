package com.diyonfinesco.todo.repository;

import com.diyonfinesco.todo.model.entity.TodoEntity;
import com.diyonfinesco.todo.model.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends MongoRepository<TodoEntity, String> {
    TodoEntity findByTitleIgnoreCase(String title);

    TodoEntity findByTitleIgnoreCaseAndIdNot(String title, String id);

    List<TodoEntity> findAllByUser(UserEntity user);

    Optional<TodoEntity> findByIdAndUser(String id, UserEntity user);
}
