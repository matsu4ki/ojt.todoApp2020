package com.example.ojt.todoApp2020.service;

import com.example.ojt.todoApp2020.entity.Todo;
import com.example.ojt.todoApp2020.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;

    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }
}
