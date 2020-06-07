package com.example.ojt.todoApp2020.repository;

import com.example.ojt.todoApp2020.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String name);
}
