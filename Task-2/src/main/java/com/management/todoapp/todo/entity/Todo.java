package com.management.todoapp.todo.entity;

import com.management.todoapp.author.entity.Author;
import com.management.todoapp.shared.annotation.*;
import com.management.todoapp.shared.entity.EntityInformation;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Todo extends EntityInformation {
    private Long todoId;

    private String todoTitle;

    @ManyToOne()
    @JoinColumn(name = "author_id")
    private Author author;

    private String password;

    public Todo(
            String todoTitle,
            Author author,
            String password
    ) {
        super(LocalDateTime.now(), LocalDateTime.now());
        this.todoTitle = todoTitle;
        this.author = author;
        this.password = password;
    }
}
