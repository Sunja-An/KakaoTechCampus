package com.management.todoapp.todo.repository;

import com.management.todoapp.todo.entity.Todo;

import java.util.List;

public interface TodoRepository {
    Todo findById(Long id);
    List<Todo> findAll();
}
