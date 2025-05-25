package com.management.todoapp.author.service;

import com.management.todoapp.author.entity.Author;

public interface AuthorService {
    void createAuthor(Author author);
    Author getAuthorById(Integer id);
    Author getAuthorByName(String AuthorName);
}
