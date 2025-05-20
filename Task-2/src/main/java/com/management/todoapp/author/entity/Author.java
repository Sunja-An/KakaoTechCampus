package com.management.todoapp.author.entity;

import com.management.todoapp.shared.entity.EntityInformation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Author extends EntityInformation {
    private Long authorId;

    private String authorName;

    private String authorEmail;
}
