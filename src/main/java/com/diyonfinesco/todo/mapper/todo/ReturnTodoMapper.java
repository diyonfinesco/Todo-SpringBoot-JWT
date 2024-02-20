package com.diyonfinesco.todo.mapper.todo;

import com.diyonfinesco.todo.dto.todo.ReturnTodoDTO;
import com.diyonfinesco.todo.mapper.AbstractMapper;
import com.diyonfinesco.todo.model.entity.TodoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReturnTodoMapper extends AbstractMapper<TodoEntity, ReturnTodoDTO> {
}
