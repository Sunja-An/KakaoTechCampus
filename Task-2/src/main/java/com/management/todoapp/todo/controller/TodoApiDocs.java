package com.management.todoapp.todo.controller;

import com.management.todoapp.shared.domain.Pageable;
import com.management.todoapp.todo.dto.request.RequestModifyTodoDto;
import com.management.todoapp.todo.dto.request.RequestPasswordDto;
import com.management.todoapp.todo.dto.request.RequestTodoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

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
