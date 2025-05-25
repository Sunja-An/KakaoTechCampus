package com.management.todoapp.todo.entity;

import com.management.todoapp.author.entity.Author;
import com.management.todoapp.shared.annotation.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Todo {
    @Id
    private Integer todoId;

    @NotNull
    @Setter
    private String todoTitle;

    @JoinColumn(name = "author_id")
    private Author author;

    @NotNull
    private String password;

    private LocalDateTime createdAt;

    @Setter
    private LocalDateTime updatedAt;

    public Todo(
            String todoTitle,
            Author author,
            String password
    ) {
        this.todoTitle = todoTitle;
        this.author = author;
        this.password = password;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
