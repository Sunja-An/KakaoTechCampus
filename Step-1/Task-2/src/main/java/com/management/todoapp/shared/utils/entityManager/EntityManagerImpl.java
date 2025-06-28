package com.management.todoapp.shared.utils.entityManager;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class EntityManagerImpl<T> implements EntityManager {
    private ConcurrentHashMap<String, Object> entities;

    @PostConstruct
    public void init(){
        this.entities = new ConcurrentHashMap<>();

    }

    @Override
    public void persist(Object entity) {

    }

    @Override
    public void merge(Object entity) {

    }

    @Override
    public void remove(Object entity) {

    }
}
