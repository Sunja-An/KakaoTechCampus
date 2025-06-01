package com.management.todoapp.shared.exception;

public class PasswordMismatchException extends IllegalArgumentException {
    public PasswordMismatchException(String message) {
        super(message);
    }
}
