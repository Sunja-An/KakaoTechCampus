package com.management.todoapp.author.entity;

import com.management.todoapp.shared.annotation.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Author {
    @Id
    private Integer authorId;

    @NotNull
    private String authorName;

    @Email
    private String authorEmail;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Author(Integer authorId) {
        this.authorId = authorId;
    }

    public Author(
            String authorName,
            String authorEmail
    ) {
        this.authorName = authorName;
        this.authorEmail = authorEmail;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Author(
            LocalDateTime updatedAt
    ){
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", authorEmail='" + authorEmail + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
