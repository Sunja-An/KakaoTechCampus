package com.management.todoapp.todo.dto.request;

public record RequestModifyTodoDto(
        String title,
        String authorName,
        String password
) {
}
