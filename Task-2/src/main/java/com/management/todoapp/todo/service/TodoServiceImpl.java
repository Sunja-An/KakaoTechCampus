package com.management.todoapp.todo.service;

import com.management.todoapp.todo.dto.response.ResponseTodoDto;
import com.management.todoapp.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;

    @Override
    public ResponseTodoDto getTodo(String id) {
        try{
            todoRepository.findById((int) Long.parseLong(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
