package com.management.todoapp.shared.filter;

import com.management.todoapp.shared.exception.ContentLengthException;
import com.management.todoapp.shared.exception.NoAuthorException;
import com.management.todoapp.shared.exception.NoTodoException;
import com.management.todoapp.shared.exception.PasswordMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<String> handlePasswordMismatchException(IllegalArgumentException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(ContentLengthException.class)
    public ResponseEntity<String> handleContentLengthException(ContentLengthException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(NoAuthorException.class)
    public ResponseEntity<String> handleNoAuthorException(NoAuthorException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(NoTodoException.class)
    public ResponseEntity<String> handleNoTodoException(NoTodoException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
