package com.management.todoapp.todo.controller;

import com.management.todoapp.todo.entity.Todo;
import com.management.todoapp.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/todo")
@RequiredArgsConstructor
public class TodoController implements TodoApiDocs{
    private final TodoService todoService;

    @Override
    @PostMapping("")
    public ResponseEntity<?> addTodo(String title, String description) {
        todoService.getTodo(title);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    @GetMapping("")
    public ResponseEntity<?> getTodos() {
        return null;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> getTodo(@PathVariable String id) {
        return null;
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable String id) {
        return null;
    }
}
