package com.example.ojt.todoApp2020.controller;

import com.example.ojt.todoApp2020.component.SessionData;
import com.example.ojt.todoApp2020.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TodoController {

    private final TodoService todoService;
    private final SessionData sessionData;

    @Autowired
    public TodoController(TodoService todoService, SessionData sessionData) {
        this.todoService = todoService;
        this.sessionData = sessionData;
    }

    /**
     * Topページ(ログイン後のページ)
     * @return トップページ
     */
    @RequestMapping("/")
    public String index(@AuthenticationPrincipal OidcUser user, Model model) {
        // sessionにユーザ情報を保存する
        model.addAttribute("user", sessionData.getUser());
        model.addAttribute("todoList", todoService.findAll());
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        return "create";
    }
}
