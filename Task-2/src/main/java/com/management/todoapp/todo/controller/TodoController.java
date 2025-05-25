package com.management.todoapp.todo.controller;

import com.management.todoapp.todo.dto.request.RequestModifyTodoDto;
import com.management.todoapp.todo.dto.request.RequestPasswordDto;
import com.management.todoapp.todo.dto.request.RequestTodoDto;
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
    public ResponseEntity<?> addTodo(@RequestBody RequestTodoDto requestTodoDto) {
        todoService.createTodo(requestTodoDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Pagination 미처리
    @Override
    @GetMapping("")
    public ResponseEntity<?> getTodos() {
        return null;
    }

    /*
    // Pagination 처리

    @Override
    @GetMapping("")
    public ResponseEntity<?> getTodos() {
        return null;
    }
    */

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> getTodo(@PathVariable String id) {
        return new ResponseEntity<>(todoService.getTodo(id), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodo(
            @PathVariable String id,
            @RequestBody RequestPasswordDto requestPasswordDto
    ) {
        todoService.deleteTodo(id, requestPasswordDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateTodo(
            @PathVariable String id,
            @RequestBody RequestModifyTodoDto requestModifyTodoDto
    ) {
        todoService.updateTodo(id, requestModifyTodoDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
