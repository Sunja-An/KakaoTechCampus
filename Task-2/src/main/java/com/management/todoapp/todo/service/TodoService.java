package com.management.todoapp.todo.service;

import com.management.todoapp.shared.domain.Pageable;
import com.management.todoapp.todo.dto.request.RequestModifyTodoDto;
import com.management.todoapp.todo.dto.request.RequestPasswordDto;
import com.management.todoapp.todo.dto.request.RequestTodoDto;
import com.management.todoapp.todo.dto.response.ResponseTodoDto;

import java.util.List;

public interface TodoService {
    ResponseTodoDto getTodo(String id);
    List<ResponseTodoDto> getAllTodos();
    List<ResponseTodoDto> getPagingTodos(Pageable pageable);
    void createTodo(RequestTodoDto requestTodoDto);
    void deleteTodo(String id, RequestPasswordDto password);
    void updateTodo(String id, RequestModifyTodoDto requestTodoDto);
}
