package com.management.todoapp.todo.service;

import com.management.todoapp.author.entity.Author;
import com.management.todoapp.author.service.AuthorService;
import com.management.todoapp.todo.dto.request.RequestModifyTodoDto;
import com.management.todoapp.todo.dto.request.RequestTodoDto;
import com.management.todoapp.todo.dto.response.ResponseTodoDto;
import com.management.todoapp.todo.entity.Todo;
import com.management.todoapp.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final AuthorService authorService;

    @Override
    public ResponseTodoDto getTodo(String id) {
        try{
            Optional<Todo> todo = todoRepository.findById(Integer.parseInt(id));
            return todo.map(ResponseTodoDto::from).orElse(null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createTodo(RequestTodoDto requestTodoDto) {
        try{
            if(requestTodoDto.title().length() > 200){
                throw new RuntimeException("[ERROR] Title is too long");
            }
            Todo todo = new Todo(
                    requestTodoDto.title(),
                    authorService.getAuthorByName(requestTodoDto.author()),
                    requestTodoDto.password()
            );
            System.out.println("todo = " + todo.getAuthor());
            todoRepository.save(todo);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteTodo(String id, String password) {
        try{
            Optional<Todo> todo = todoRepository.findById(Integer.parseInt(id));
            if(todo.isPresent()){
                Todo todoObject = todo.get();
                if(!todoObject.getPassword().equals(password)) {
                    throw new RuntimeException("[ERROR] Wrong password");
                }
                todoRepository.deleteById(Integer.parseInt(id));
            }
            throw new RuntimeException("[ERROR] Wrong id");
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseTodoDto updateTodo(String id, RequestModifyTodoDto requestTodoDto) {
        try{
            Optional<Todo> todo = todoRepository.findById(Integer.parseInt(id));
            if(todo.isPresent()){
                Todo todoObject = todo.get();
                if(!requestTodoDto.password().equals(todoObject.getPassword())) {
                    throw new RuntimeException("[ERROR] Wrong password");
                }
                return ResponseTodoDto.from(todoRepository.update(requestTodoDto));
            }
            throw new RuntimeException("[ERROR] Wrong id");
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
