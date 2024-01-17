package com.diyonfinesco.todo.controller;

import com.diyonfinesco.todo.dto.todo.CreateTodoDTO;
import com.diyonfinesco.todo.dto.todo.UpdateTodoDTO;
import com.diyonfinesco.todo.service.todo.TodoService;
import com.diyonfinesco.todo.util.CustomResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    TodoService todoService;

    @PostMapping()
    public CustomResponseEntity create(@Valid @RequestBody CreateTodoDTO todo) {
        return todoService.create(todo);
    }

    @GetMapping()
    public CustomResponseEntity findAll() {
        return todoService.findAll();
    }


    @GetMapping("/{id}")
    public CustomResponseEntity findById(@PathVariable String id) {
        return todoService.findById(id);
    }

    @PutMapping("/{id}")
    public CustomResponseEntity update(@PathVariable String id, @Valid @RequestBody UpdateTodoDTO updateTodoDTO) {
        return todoService.update(id, updateTodoDTO);
    }

    @DeleteMapping("/{id}")
    public CustomResponseEntity delete(@PathVariable String id) {
        return todoService.delete(id);
    }
}
