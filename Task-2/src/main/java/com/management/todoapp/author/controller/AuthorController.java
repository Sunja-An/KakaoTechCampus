package com.management.todoapp.author.controller;

import com.management.todoapp.author.dto.request.RequestCreateAuthor;
import com.management.todoapp.author.entity.Author;
import com.management.todoapp.author.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/author")
@RequiredArgsConstructor
public class AuthorController implements AuthorApiDocs{
    private final AuthorService authorService;

    @Override
    @PostMapping("")
    public ResponseEntity<?> createAuthor(@Valid @RequestBody RequestCreateAuthor requestCreateAuthor) {
        authorService.createAuthor(
                RequestCreateAuthor.from(requestCreateAuthor)
        );
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        return null;
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateAuthor(
            @PathVariable String id,
            @RequestBody RequestCreateAuthor author
    ) {
        return null;
    }
}
