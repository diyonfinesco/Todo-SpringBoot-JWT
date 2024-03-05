package com.diyonfinesco.todo.service.todo;

import com.diyonfinesco.todo.dto.todo.CreateTodoDTO;
import com.diyonfinesco.todo.dto.todo.UpdateTodoDTO;
import com.diyonfinesco.todo.util.CustomResponse;

public interface TodoService {
    CustomResponse create(CreateTodoDTO todoDTO);

    CustomResponse findAll();

    CustomResponse findById(String id);

    CustomResponse update(String id, UpdateTodoDTO updateTodoDTO);

    CustomResponse delete(String id);
}
