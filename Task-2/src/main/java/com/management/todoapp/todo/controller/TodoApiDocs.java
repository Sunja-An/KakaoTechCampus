package com.management.todoapp.todo.controller;

import com.management.todoapp.todo.dto.request.RequestModifyTodoDto;
import com.management.todoapp.todo.dto.request.RequestPasswordDto;
import com.management.todoapp.todo.dto.request.RequestTodoDto;
import org.springframework.http.ResponseEntity;

public interface TodoApiDocs {
    ResponseEntity<?> addTodo(RequestTodoDto requestTodoDto);

    ResponseEntity<?> getTodos();

    ResponseEntity<?> getTodosPagination(
            int page,
            int size,
            Integer authorId,
            String updatedAt
    );

    ResponseEntity<?> getTodo(String id);

    ResponseEntity<?> deleteTodo(String id, RequestPasswordDto password);

    ResponseEntity<?> updateTodo(String id, RequestModifyTodoDto requestModifyTodoDto);
}
