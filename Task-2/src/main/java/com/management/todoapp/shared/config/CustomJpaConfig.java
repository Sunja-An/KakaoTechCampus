package com.management.todoapp.shared.config;

import com.management.todoapp.author.entity.Author;
import com.management.todoapp.author.repository.AuthorRepository;
import com.management.todoapp.shared.utils.jpaRepository.JpaRepositoryImpl;
import com.management.todoapp.todo.entity.Todo;
import com.management.todoapp.todo.repository.TodoRepository;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomJpaConfig {

    @Bean
    public AuthorRepository authorRepository(){
        JpaRepositoryImpl<Author, Long> authRepositoryImpl = new JpaRepositoryImpl<>(Author.class);
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(authRepositoryImpl);
        proxyFactory.setInterfaces(AuthorRepository.class);
        return (AuthorRepository) proxyFactory.getProxy();
    }

    @Bean
    public TodoRepository todoRepository(){
        JpaRepositoryImpl<Todo, Long> todoRepositoryImpl = new JpaRepositoryImpl<>(Todo.class);
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(todoRepositoryImpl);
        proxyFactory.setInterfaces(TodoRepository.class);
        return (TodoRepository) proxyFactory.getProxy();
    }
}
