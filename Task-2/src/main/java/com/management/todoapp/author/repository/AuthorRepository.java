package com.management.todoapp.author.repository;

import com.management.todoapp.author.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    Author save(Author author);
    Optional<Author> findById(Long id);
    Optional<Author> findByUsername(String username);
    List<Author> findAll();
}
