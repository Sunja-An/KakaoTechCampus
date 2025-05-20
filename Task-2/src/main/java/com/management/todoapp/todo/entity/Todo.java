package com.management.todoapp.todo.entity;

import com.management.todoapp.author.entity.Author;
import com.management.todoapp.shared.entity.EntityInformation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Todo extends EntityInformation {
    private Long todoId;

    private String todoTitle;

    private Author author;

    private String password;
}
