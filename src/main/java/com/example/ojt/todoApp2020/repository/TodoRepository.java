package com.example.ojt.todoApp2020.repository;

import com.example.ojt.todoApp2020.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findByUserId(int id);
}
