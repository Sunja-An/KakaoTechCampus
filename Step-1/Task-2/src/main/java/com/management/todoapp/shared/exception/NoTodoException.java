package com.management.todoapp.shared.exception;

public class NoTodoException extends RuntimeException {
    public NoTodoException(String message) {
        super(message);
    }
}
