package com.management.todoapp.todo.dto.response;

import com.management.todoapp.todo.entity.Todo;

public record ResponseTodoDto(
        String todoTitle,
        String authorName,
        String updatedAt
) {
    public static ResponseTodoDto from(Todo todo){
        return new ResponseTodoDto(
                todo.getTodoTitle(),
                todo.getAuthor().getAuthorName(),
                todo.getUpdatedAt().toString()
        );
    };
}
