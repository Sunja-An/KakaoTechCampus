package com.management.todoapp.author.dto.request;

public record RequestCreateAuthor(
        String authorName,
        String authorEmail
) {
}
