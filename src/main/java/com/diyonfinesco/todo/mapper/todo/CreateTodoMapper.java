package com.diyonfinesco.todo.mapper.todo;

import com.diyonfinesco.todo.dto.todo.CreateTodoDTO;
import com.diyonfinesco.todo.mapper.AbstractMapper;
import com.diyonfinesco.todo.model.entity.TodoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateTodoMapper extends AbstractMapper<TodoEntity, CreateTodoDTO> {
}
