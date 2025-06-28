package com.management.todoapp.todo.dto.request;

public record RequestTodoDto(
    String title,
    String author,
    String password
) {
}
