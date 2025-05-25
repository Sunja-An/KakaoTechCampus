package com.management.todoapp.author.service;

import com.management.todoapp.author.dto.response.ResponseCreatedAuthor;
import com.management.todoapp.author.entity.Author;

public interface AuthorService {
    void createAuthor(Author author);
    Author getAuthorByName(String AuthorName);
}
