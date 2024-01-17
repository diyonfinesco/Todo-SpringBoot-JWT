package com.diyonfinesco.todo.service.todo;

import com.diyonfinesco.todo.dto.todo.CreateTodoDTO;
import com.diyonfinesco.todo.dto.todo.UpdateTodoDTO;
import com.diyonfinesco.todo.util.CustomResponseEntity;

public interface TodoService {
    CustomResponseEntity create(CreateTodoDTO todoDTO);

    CustomResponseEntity findAll();

    CustomResponseEntity findById(String id);

    CustomResponseEntity update(String id, UpdateTodoDTO updateTodoDTO);

    CustomResponseEntity delete(String id);
}
