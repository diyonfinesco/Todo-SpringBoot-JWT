package com.diyonfinesco.todo.service.todo;

import com.diyonfinesco.todo.dto.todo.CreateTodoDTO;
import com.diyonfinesco.todo.dto.todo.ReturnTodoDTO;
import com.diyonfinesco.todo.dto.todo.UpdateTodoDTO;
import com.diyonfinesco.todo.mapper.todo.CreateTodoMapper;
import com.diyonfinesco.todo.mapper.todo.ReturnTodoMapper;
import com.diyonfinesco.todo.mapper.todo.UpdateTodoMapper;
import com.diyonfinesco.todo.model.entity.TodoEntity;
import com.diyonfinesco.todo.repository.TodoRepository;
import com.diyonfinesco.todo.service.user.UserService;
import com.diyonfinesco.todo.util.CustomResponse;
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
    private ReturnTodoMapper returnTodoMapper;

    @Autowired
    private UserService userService;

    @Override
    public CustomResponse create(CreateTodoDTO todoDTO) {
        TodoEntity isExist = todoRepository.findByTitleIgnoreCase(todoDTO.getTitle());

        if (isExist != null) {
            return new CustomResponse(HttpStatus.BAD_REQUEST, false, "Todo title already exist!");
        }

        TodoEntity todoEntity = createTodoMapper.toEntity(todoDTO);

        todoEntity.setUser(userService.getLoggedInUser());
        todoEntity.setCompleted(false);

        todoRepository.save(todoEntity);

        ReturnTodoDTO returnTodoDTO = returnTodoMapper.toDTO(todoEntity);

        return new CustomResponse(HttpStatus.CREATED, true, returnTodoDTO);
    }

    @Override
    public CustomResponse findAll() {
        List<TodoEntity> entities = todoRepository.findAllByUser(userService.getLoggedInUser());

        List<ReturnTodoDTO> returnTodoDTOList = returnTodoMapper.toDTOList(entities);

        return new CustomResponse(HttpStatus.OK, true, returnTodoDTOList);
    }

    @Override
    public CustomResponse findById(String id) {
        TodoEntity todoEntity = todoRepository.findByIdAndUser(id, userService.getLoggedInUser()).orElse(null);

        if (todoEntity == null) {
            return new CustomResponse(HttpStatus.NOT_FOUND, false, "Todo not found!");
        }

        ReturnTodoDTO returnTodoDTO = returnTodoMapper.toDTO(todoEntity);

        return new CustomResponse(HttpStatus.OK, true, returnTodoDTO);
    }

    @Override
    public CustomResponse update(String id, UpdateTodoDTO updateTodoDTO) {

        TodoEntity todo = todoRepository.findByIdAndUser(id, userService.getLoggedInUser()).orElse(null);

        if (todo == null) {
            return new CustomResponse(HttpStatus.NOT_FOUND, false, "Todo not found!");
        }

        TodoEntity isExistBySameTitle = todoRepository.findByTitleIgnoreCaseAndIdNot(updateTodoDTO.getTitle(), id);

        if (isExistBySameTitle != null) {
            return new CustomResponse(HttpStatus.NOT_FOUND, false, "Todo title already exist!");
        }

        TodoEntity updateTodo = updateTodoMapper.toEntity(updateTodoDTO);

        todo.setTitle(updateTodo.getTitle());
        todo.setCompleted(updateTodo.getCompleted());

        todoRepository.save(todo);

        ReturnTodoDTO returnTodoDTO = returnTodoMapper.toDTO(todo);

        return new CustomResponse(HttpStatus.OK, true, returnTodoDTO);
    }

    @Override
    public CustomResponse delete(String id) {

        TodoEntity todo = todoRepository.findByIdAndUser(id, userService.getLoggedInUser()).orElse(null);

        if (todo == null) {
            return new CustomResponse(HttpStatus.NOT_FOUND, false, "Todo not found!");
        }

        todoRepository.delete(todo);

        return new CustomResponse(HttpStatus.OK, true, "Todo deleted");
    }
}
