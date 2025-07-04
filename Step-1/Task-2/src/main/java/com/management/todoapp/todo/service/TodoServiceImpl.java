package com.management.todoapp.todo.service;

import com.management.todoapp.author.entity.Author;
import com.management.todoapp.author.service.AuthorService;
import com.management.todoapp.shared.domain.Pageable;
import com.management.todoapp.shared.exception.ContentLengthException;
import com.management.todoapp.shared.exception.NoAuthorException;
import com.management.todoapp.shared.exception.NoTodoException;
import com.management.todoapp.shared.exception.PasswordMismatchException;
import com.management.todoapp.todo.dto.request.RequestModifyTodoDto;
import com.management.todoapp.todo.dto.request.RequestPasswordDto;
import com.management.todoapp.todo.dto.request.RequestTodoDto;
import com.management.todoapp.todo.dto.response.ResponseTodoDto;
import com.management.todoapp.todo.entity.Todo;
import com.management.todoapp.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
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
            if(todo.isEmpty()){
                return null;
            }
            Todo todoObject = todo.get();

            todoObject.setAuthor(
                    authorService.getAuthorById(todoObject.getAuthor().getAuthorId())
            );

            return todo.map(ResponseTodoDto::from).orElse(null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createTodo(RequestTodoDto requestTodoDto) {
        try{
            if(requestTodoDto.title().length() > 200){
                throw new ContentLengthException("[ERROR] Title is too long");
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
    public void deleteTodo(String id, RequestPasswordDto requestPasswordDto) {
        try{
            Optional<Todo> todo = todoRepository.findById(Integer.parseInt(id));
            if(todo.isPresent()){
                Todo todoObject = todo.get();
                System.out.println("todoObject = " + todoObject.getPassword());
                System.out.println("password = " + requestPasswordDto.password());
                if(!todoObject.getPassword().equals(requestPasswordDto.password())) {
                    throw new PasswordMismatchException("[ERROR] Wrong password");
                }
                todoRepository.deleteById(Integer.parseInt(id));
                return;
            }
            throw new NoTodoException("[ERROR] Wrong id");
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ResponseTodoDto> getAllTodos() {
        try{
            List<Todo> todos = todoRepository.findAll();
            for(Todo todo: todos){
                todo.setAuthor(
                        authorService.getAuthorById(todo.getAuthor().getAuthorId())
                );
            }
            return todos.stream().map(ResponseTodoDto::from).toList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ResponseTodoDto> getPagingTodos(Pageable pageable) {
        try{
            List<Todo> todos = todoRepository.findAll(pageable);
            for(Todo todo: todos){
                todo.setAuthor(
                        authorService.getAuthorById(todo.getAuthor().getAuthorId())
                );
            }
            return todos.stream().map(ResponseTodoDto::from).toList();
        } catch (SQLException e) {
                throw new RuntimeException(e);
        }

    }

    @Override
    public void updateTodo(String id, RequestModifyTodoDto requestTodoDto) {
        try{
            Optional<Todo> todo = todoRepository.findById(Integer.parseInt(id));
            Author author;
            if(todo.isPresent()){
                author = authorService.getAuthorByName(requestTodoDto.authorName());
                if(author == null){
                    throw new NoAuthorException("[ERROR] Author not found");
                }
                Todo updatedObject = new Todo(
                        Integer.parseInt(id),
                        requestTodoDto.todoTitle(),
                        author
                );
                Todo todoObject = todo.get();
                if(!requestTodoDto.password().equals(todoObject.getPassword())) {
                    throw new PasswordMismatchException("[ERROR] Wrong password");
                }
                todoRepository.update(updatedObject);
                return;
            }
            throw new NoTodoException("[ERROR] Wrong id");
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
