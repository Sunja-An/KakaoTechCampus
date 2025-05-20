package com.management.todoapp.todo.controller;

import org.springframework.http.ResponseEntity;

public interface TodoApiDocs {
    ResponseEntity<?> addTodo(
            String title,
            String description
    );

    ResponseEntity<?> getTodos();

    ResponseEntity<?> getTodo(String id);

    ResponseEntity<?> deleteTodo(String id);
}
