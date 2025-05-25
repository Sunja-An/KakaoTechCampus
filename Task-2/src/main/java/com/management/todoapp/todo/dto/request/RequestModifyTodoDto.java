package com.management.todoapp.todo.dto.request;

import java.time.LocalDateTime;

public record RequestModifyTodoDto(
        String todoTitle,
        String authorName,
        String password,
        String updatedAt,
        Integer id
) {
    public static RequestModifyTodoDto of(Integer id, RequestModifyTodoDto requestModifyTodoDto) {
        return new RequestModifyTodoDto(
                requestModifyTodoDto.todoTitle(),
                requestModifyTodoDto.authorName(),
                requestModifyTodoDto.password(),
                LocalDateTime.now().toString(),
                id
        );
    }
}
