package com.management.todoapp.author.controller;

import com.management.todoapp.author.dto.request.RequestCreateAuthor;
import com.management.todoapp.author.entity.Author;
import org.springframework.http.ResponseEntity;

public interface AuthorApiDocs {
    ResponseEntity<?> createAuthor(RequestCreateAuthor author);
    ResponseEntity<?> deleteAuthor(Long id);
    ResponseEntity<?> updateAuthor(String id, RequestCreateAuthor author);
}
