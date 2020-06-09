package com.example.ojt.todoApp2020.service;

import com.example.ojt.todoApp2020.component.SessionData;
import com.example.ojt.todoApp2020.entity.Todo;
import com.example.ojt.todoApp2020.form.CreateTodoForm;
import com.example.ojt.todoApp2020.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final SessionData sessionData;

    @Autowired
    public TodoService(TodoRepository todoRepository, SessionData sessionData) {
        this.todoRepository = todoRepository;
        this.sessionData = sessionData;
    }

    public List<Todo> findByUserId(int id) {
        return todoRepository.findByUserId(id);
    }

    @Transactional
    public void createTodoByForm(CreateTodoForm createTodoForm) {
        var todo = new Todo();
        todo.setTitle(createTodoForm.getTitle());
        todo.setContent(createTodoForm.getContent());
        todo.setCreatedUser(sessionData.getUser().getId());
        todo.setUpdatedUser(sessionData.getUser().getId());
        todo.setUserId(sessionData.getUser().getId());
        todoRepository.save(todo);
    }

    @Transactional
    public void delete(int id) {
        todoRepository.deleteById(id);
    }
}
