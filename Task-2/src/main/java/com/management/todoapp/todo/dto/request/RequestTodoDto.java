package com.management.todoapp.todo.dto.request;

import com.management.todoapp.todo.entity.Todo;

public record RequestTodoDto(
    String title,
    String password
) {
    public static RequestTodoDto from(Todo todo){
        return new RequestTodoDto(
                todo.getTodoTitle(),
                todo.getPassword()
        );
    }
}
