package com.management.todoapp.shared.config;

import com.management.todoapp.author.entity.Author;
import com.management.todoapp.author.repository.AuthorRepository;
import com.management.todoapp.shared.utils.JpaRepository;
import com.management.todoapp.shared.utils.JpaRepositoryImpl;
import com.management.todoapp.todo.entity.Todo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomJpaConfig {

    @Bean
    public AuthorRepository authorRepository(){
        return (AuthorRepository) new JpaRepositoryImpl<Author, Long>(Author.class);
    }

    @Bean
    public JpaRepository<Todo, Long> todoRepository(){
        return new JpaRepositoryImpl<Todo, Long>(Todo.class);
    }
}
