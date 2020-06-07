package com.example.ojt.todoApp2020.entity;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class AbstractEntity implements Serializable {

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void onPrePersist() {
        // saveが呼び出された際、保存される前に走る
        setCreatedAt(LocalDateTime.now());
        setUpdatedAt(LocalDateTime.now());
    }

    @PreUpdate
    public void onPreUpdate() {
        setUpdatedAt(LocalDateTime.now());
    }
}
