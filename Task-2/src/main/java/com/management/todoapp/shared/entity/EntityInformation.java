package com.management.todoapp.shared.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EntityInformation {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public EntityInformation(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
