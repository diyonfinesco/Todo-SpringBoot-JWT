package com.diyonfinesco.todo.service.todo;

import com.diyonfinesco.todo.dto.todo.CreateTodoDTO;
import com.diyonfinesco.todo.dto.todo.UpdateTodoDTO;
import com.diyonfinesco.todo.mapper.todo.CreateTodoMapper;
import com.diyonfinesco.todo.mapper.todo.DefaultTodoMapper;
import com.diyonfinesco.todo.mapper.todo.UpdateTodoMapper;
import com.diyonfinesco.todo.model.entity.TodoEntity;
import com.diyonfinesco.todo.repository.TodoRepository;
import com.diyonfinesco.todo.util.CustomResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private CreateTodoMapper createTodoMapper;

    @Autowired
    private UpdateTodoMapper updateTodoMapper;

    @Autowired
    private DefaultTodoMapper defaultTodoMapper;

    @Override
    public CustomResponseEntity create(CreateTodoDTO todoDTO) {
        TodoEntity isExist = todoRepository.findByTitle(todoDTO.getTitle());

        if (isExist != null) {
            return new CustomResponseEntity(HttpStatus.BAD_REQUEST.value(), false, "Todo title already exist!");
        }

        TodoEntity todoEntity = createTodoMapper.toEntity(todoDTO);

        return new CustomResponseEntity(HttpStatus.CREATED.value(), true, defaultTodoMapper.toDTO(todoRepository.save(todoEntity)));
    }

    @Override
    public CustomResponseEntity findAll() {
        List<TodoEntity> entities = todoRepository.findAll();
        return new CustomResponseEntity(HttpStatus.OK.value(), true, defaultTodoMapper.toDTOList(entities));
    }

    @Override
    public CustomResponseEntity findById(String id) {
        TodoEntity todoEntity = todoRepository.findById(id).orElse(null);

        if (todoEntity == null) {
            return new CustomResponseEntity(HttpStatus.NOT_FOUND.value(), false, "Todo not found!");
        }

        return new CustomResponseEntity(HttpStatus.OK.value(), true, defaultTodoMapper.toDTO(todoEntity));
    }

    @Override
    public CustomResponseEntity update(String id, UpdateTodoDTO updateTodoDTO) {
        TodoEntity isExist = todoRepository.findById(id).orElse(null);

        if (isExist == null) {
            return new CustomResponseEntity(HttpStatus.NOT_FOUND.value(), false, "Todo not found!");
        }

        TodoEntity isExistBySameTitle = todoRepository.findByTitleAndIdNot(updateTodoDTO.getTitle(), updateTodoDTO.getId());

        if (isExistBySameTitle != null) {
            return new CustomResponseEntity(HttpStatus.NOT_FOUND.value(), false, "Todo title already exist!");
        }

        TodoEntity todoEntity = updateTodoMapper.toEntity(updateTodoDTO);

        todoRepository.save(todoEntity);

        return new CustomResponseEntity(HttpStatus.OK.value(), true, defaultTodoMapper.toDTO(todoEntity));
    }

    @Override
    public CustomResponseEntity delete(String id) {
        TodoEntity isExist = todoRepository.findById(id).orElse(null);

        if (isExist == null) {
            return new CustomResponseEntity(HttpStatus.NOT_FOUND.value(), false, "Todo not found!");
        }

        todoRepository.delete(isExist);
        return new CustomResponseEntity(HttpStatus.OK.value(), true, "Todo deleted");
    }
}
