package com.management.todoapp.shared.utils.jpaRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface JpaRepository<T, U> {
    Optional<T> findById(U id) throws SQLException;
    Optional<T> findByAuthorName(String authorName) throws SQLException;
    List<T> findAll() throws SQLException;
    void save(Object object) throws SQLException;
    void update(Object object) throws SQLException;
    void deleteById(U id) throws SQLException;
    void close();
}
