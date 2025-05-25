package com.management.todoapp.author.controller;

import com.management.todoapp.author.dto.request.RequestCreateAuthor;
import org.springframework.http.ResponseEntity;

public interface AuthorApiDocs {
    ResponseEntity<?> createAuthor(RequestCreateAuthor author);
}
