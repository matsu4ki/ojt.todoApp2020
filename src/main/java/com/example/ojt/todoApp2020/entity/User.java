package com.example.ojt.todoApp2020.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username; // username = email
    private String password;
    private String role;
    private boolean isEnabled;
    private int createdUser;
    private int updatedUser;

    @PrePersist
    public void onPrePersist() {
        super.onPrePersist();
        setRole("USER");
        setEnabled(true);
    }

    @PostPersist
    public void onPostPresist() {
        setCreatedUser(getId());
        setUpdatedUser(getId());
    }
}
