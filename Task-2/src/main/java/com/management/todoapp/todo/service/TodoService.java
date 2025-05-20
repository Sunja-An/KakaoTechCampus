package com.management.todoapp.todo.service;

import com.management.todoapp.todo.dto.response.ResponseTodoDto;

public interface TodoService {
    ResponseTodoDto getTodo(String id);
}
