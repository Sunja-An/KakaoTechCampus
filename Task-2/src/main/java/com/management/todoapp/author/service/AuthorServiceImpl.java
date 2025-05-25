package com.management.todoapp.author.service;

import com.management.todoapp.author.dto.response.ResponseCreatedAuthor;
import com.management.todoapp.author.entity.Author;
import com.management.todoapp.author.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public void createAuthor(Author author) {
        try{
            authorRepository.save(author);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Author getAuthorByName(String AuthorName) {
        try{
            Optional<Author> author = authorRepository.findByAuthorName(AuthorName);
            return author.orElse(null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
