package com.management.todoapp.todo.entity;

import com.management.todoapp.author.entity.Author;
import com.management.todoapp.shared.annotation.*;
import jakarta.validation.constraints.Max;
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
    @Max(200)
    private String todoTitle;

    @Setter
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

    public Todo(
            Integer todoId,
            String todoTitle,
            Author author
    ){
        this.todoId = todoId;
        this.todoTitle = todoTitle;
        this.author = author;
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Todo{" +
                "todoId=" + todoId +
                ", todoTitle='" + todoTitle + '\'' +
                ", author=" + author +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
