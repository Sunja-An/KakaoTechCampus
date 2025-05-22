package com.management.todoapp.author.entity;

import com.management.todoapp.shared.annotation.*;
import com.management.todoapp.shared.entity.EntityInformation;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "author")
@NoArgsConstructor
public class Author extends EntityInformation {
    private Long authorId;

    private String authorName;

    private String authorEmail;

    public Author(
            String authorName,
            String authorEmail
    ) {
        super(LocalDateTime.now(), LocalDateTime.now());
        this.authorName = authorName;
        this.authorEmail = authorEmail;
    }
}
