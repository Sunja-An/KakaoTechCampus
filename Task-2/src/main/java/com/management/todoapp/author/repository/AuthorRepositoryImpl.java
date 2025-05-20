package com.management.todoapp.author.repository;

import com.management.todoapp.author.entity.Author;
import com.management.todoapp.shared.utils.JDBCTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {
    @Override
    public Author save(Author author) {
        JDBCTemplate sqlMapper = new JDBCTemplate();

        return null;
    }

    @Override
    public Optional<Author> findById(Long id) {
        JDBCTemplate sqlMapper = new JDBCTemplate();
        return Optional.empty();
    }

    @Override
    public Optional<Author> findByUsername(String username) {
        JDBCTemplate sqlMapper = new JDBCTemplate();
        return Optional.empty();
    }

    @Override
    public List<Author> findAll() {
        JDBCTemplate sqlMapper = new JDBCTemplate();
        return List.of();
    }
}
