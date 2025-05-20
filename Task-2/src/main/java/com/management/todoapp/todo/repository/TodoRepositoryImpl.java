package com.management.todoapp.todo.repository;

import com.management.todoapp.todo.entity.Todo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoRepositoryImpl implements TodoRepository {

    @Override
    public Todo findById(Long id) {

        return null;
    }

    @Override
    public List<Todo> findAll() {
        return List.of();
    }
}
