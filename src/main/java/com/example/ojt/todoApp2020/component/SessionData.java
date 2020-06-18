package com.example.ojt.todoApp2020.component;

import com.example.ojt.todoApp2020.entity.User;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;


@Data
@Component
@SessionScope
public class SessionData implements Serializable {
    private static final long serialVersionUID = 1L;
    User user;
}
