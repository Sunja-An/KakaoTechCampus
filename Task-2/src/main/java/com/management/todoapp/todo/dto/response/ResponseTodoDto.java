package com.management.todoapp.todo.dto.response;

import com.management.todoapp.todo.entity.Todo;

public record ResponseTodoDto(
        String todoTitle,
        String authorName
) {
    public static ResponseTodoDto from(Todo todo){
        return new ResponseTodoDto(
                todo.getTodoTitle(),
                todo.getAuthor().getAuthorName()
        );
    };
}
