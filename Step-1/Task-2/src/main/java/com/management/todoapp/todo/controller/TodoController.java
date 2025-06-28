package com.management.todoapp.todo.controller;

import com.management.todoapp.shared.domain.Pageable;
import com.management.todoapp.todo.dto.request.RequestModifyTodoDto;
import com.management.todoapp.todo.dto.request.RequestPasswordDto;
import com.management.todoapp.todo.dto.request.RequestTodoDto;
import com.management.todoapp.todo.dto.response.ResponseTodoDto;
import com.management.todoapp.todo.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/todo")
@RequiredArgsConstructor
public class TodoController implements TodoApiDocs{
    private final TodoService todoService;

    @Override
    @PostMapping("")
    public ResponseEntity<?> addTodo(@Valid @RequestBody RequestTodoDto requestTodoDto) {
        if(requestTodoDto.title().length() > 200){
            return ResponseEntity.badRequest().body("200자를 넘으면 안됩니다.");
        }
        if(requestTodoDto.password().isEmpty()){
            return ResponseEntity.badRequest().body("비밀번호가 필수입니다.");
        }
        todoService.createTodo(requestTodoDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Pagination 미처리
    @Override
    @GetMapping("")
    public ResponseEntity<?> getTodos() {
        return ResponseEntity.ok(todoService.getAllTodos());
    }

    // Pagination 처리
    @Override
    @GetMapping("/pagination")
    public ResponseEntity<?> getTodosPagination(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) Integer authorId,
            @RequestParam(required = false) String updatedAt
    ) {
        if(page < 0 || size < 0){
            return ResponseEntity.ok(List.of());
        }
        Pageable pageable = new Pageable(authorId, updatedAt, size, page);
        List<ResponseTodoDto> todo = todoService.getPagingTodos(pageable);
        return ResponseEntity.ok(todo);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> getTodo(@PathVariable String id) {
        return new ResponseEntity<>(todoService.getTodo(id), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodo(
            @PathVariable String id,
            @RequestBody RequestPasswordDto requestPasswordDto
    ) {
        if(requestPasswordDto.password().isEmpty()){
            return ResponseEntity.badRequest().body("비밀번호가 필수입니다.");
        }
        todoService.deleteTodo(id, requestPasswordDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateTodo(
            @PathVariable String id,
            @RequestBody RequestModifyTodoDto requestModifyTodoDto
    ) {
        if(requestModifyTodoDto.password().isEmpty()){
            return ResponseEntity.badRequest().body("비밀번호가 필수입니다.");
        }
        todoService.updateTodo(id, requestModifyTodoDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
