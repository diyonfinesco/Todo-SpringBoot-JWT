package com.diyonfinesco.todo.mapper.user;

import com.diyonfinesco.todo.dto.user.DefaultUserDTO;
import com.diyonfinesco.todo.mapper.AbstractMapper;
import com.diyonfinesco.todo.model.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DefaultUserMapper extends AbstractMapper<UserEntity, DefaultUserDTO> {
}
