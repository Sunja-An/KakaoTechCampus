package com.management.todoapp.author.dto.response;

import com.management.todoapp.author.entity.Author;

public record ResponseCreatedAuthor(
        String authorName,
        String authorEmail
) {
    public static ResponseCreatedAuthor of(Author author) {
        return new ResponseCreatedAuthor(author.getAuthorName(), author.getAuthorName());
    }
}
