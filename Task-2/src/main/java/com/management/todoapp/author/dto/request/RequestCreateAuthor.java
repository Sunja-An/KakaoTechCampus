package com.management.todoapp.author.dto.request;

import com.management.todoapp.author.entity.Author;

public record RequestCreateAuthor(
        String authorName,
        String authorEmail
) {
    public static Author from(RequestCreateAuthor dto) {
        return new Author(
                dto.authorName(),
                dto.authorEmail()
        );
    }
}
