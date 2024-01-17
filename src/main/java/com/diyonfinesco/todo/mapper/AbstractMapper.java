package com.diyonfinesco.todo.mapper;

import java.util.List;

public interface AbstractMapper<Entity, DTO> {
    Entity toEntity(DTO dto);

    DTO toDTO(Entity entity);

    List<Entity> toEntityList(List<DTO> dtoList);

    List<DTO> toDTOList(List<Entity> entities);

}
