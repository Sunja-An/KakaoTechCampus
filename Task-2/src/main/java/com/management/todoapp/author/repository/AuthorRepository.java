package com.management.todoapp.author.repository;

import com.management.todoapp.author.entity.Author;
import com.management.todoapp.shared.utils.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
