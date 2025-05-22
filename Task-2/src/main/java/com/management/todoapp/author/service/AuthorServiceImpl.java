package com.management.todoapp.author.service;

import com.management.todoapp.author.dto.response.ResponseCreatedAuthor;
import com.management.todoapp.author.entity.Author;
import com.management.todoapp.author.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public ResponseCreatedAuthor createAuthor(Author author) {
        try{
            return ResponseCreatedAuthor.of(authorRepository.save(author));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
