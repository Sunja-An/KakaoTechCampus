package com.management.todoapp.author.dto.request;

import com.management.todoapp.author.entity.Author;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreateAuthor{

    @NotNull
    private String authorName;

    @Email
    private String authorEmail;

    public static Author from(RequestCreateAuthor dto) {
        return new Author(
                dto.getAuthorName(),
                dto.getAuthorEmail()
        );
    }
}
