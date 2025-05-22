package com.management.todoapp.shared.utils;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface JpaRepository<T, U> {
    Optional<T> findById(Long id) throws SQLException;
    List<T> findAll() throws SQLException;
    T save(Object object) throws SQLException;
    boolean delete(Long id) throws SQLException;

    void close();
}
