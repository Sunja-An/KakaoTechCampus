package com.management.todoapp.shared.exception;

public class NoAuthorException extends RuntimeException {
    public NoAuthorException(String message) {
        super(message);
    }
}
