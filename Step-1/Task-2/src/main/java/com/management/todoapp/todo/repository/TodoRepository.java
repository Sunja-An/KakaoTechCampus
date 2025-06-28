package com.management.todoapp.todo.repository;

import com.management.todoapp.shared.utils.jpaRepository.JpaRepository;
import com.management.todoapp.todo.entity.Todo;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
}
