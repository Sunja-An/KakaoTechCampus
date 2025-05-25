package com.management.todoapp.shared.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pageable {
    private Integer authorId;
    private String updatedAt;
    private Integer size;
    private Integer page;
}
