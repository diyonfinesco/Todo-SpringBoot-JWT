package com.diyonfinesco.todo.controller;

import com.diyonfinesco.todo.dto.todo.CreateTodoDTO;
import com.diyonfinesco.todo.dto.todo.UpdateTodoDTO;
import com.diyonfinesco.todo.service.todo.TodoService;
import com.diyonfinesco.todo.util.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    TodoService todoService;

    @PostMapping()
    public ResponseEntity<CustomResponse> create(@Valid @RequestBody CreateTodoDTO todo) {
        CustomResponse response = todoService.create(todo);
        return new ResponseEntity<>(response, response.getHttpStatusCode());
    }

    @GetMapping()
    public ResponseEntity<CustomResponse> findAll() {
        CustomResponse response = todoService.findAll();
        return new ResponseEntity<>(response, response.getHttpStatusCode());
    }


    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> findById(@PathVariable String id) {
        CustomResponse response = todoService.findById(id);
        return new ResponseEntity<>(response, response.getHttpStatusCode());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse> update(@PathVariable String id, @Valid @RequestBody UpdateTodoDTO updateTodoDTO) {
        CustomResponse response = todoService.update(id, updateTodoDTO);
        return new ResponseEntity<>(response, response.getHttpStatusCode());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> delete(@PathVariable String id) {
        CustomResponse response = todoService.delete(id);
        return new ResponseEntity<>(response, response.getHttpStatusCode());
    }
}