package com.management.todoapp.author.repository;

import com.management.todoapp.author.entity.Author;
import com.management.todoapp.shared.utils.jpaRepository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
