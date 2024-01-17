package com.diyonfinesco.todo.mapper.todo;

import com.diyonfinesco.todo.dto.todo.UpdateTodoDTO;
import com.diyonfinesco.todo.mapper.AbstractMapper;
import com.diyonfinesco.todo.model.entity.TodoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UpdateTodoMapper extends AbstractMapper<TodoEntity, UpdateTodoDTO> {
    @Mapping(target = "completed", source = "completed")
    TodoEntity toEntity(UpdateTodoDTO dto);

    @Mapping(target = "completed", source = "completed")
    UpdateTodoDTO toDTO(TodoEntity entity);
}
