package com.management.todoapp.shared.utils.entityManager;

public interface EntityManager {
    void persist(Object entity);
    void merge(Object entity);
    void remove(Object entity);
}
