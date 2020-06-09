package com.example.ojt.todoApp2020.controller;

import com.example.ojt.todoApp2020.component.SessionData;
import com.example.ojt.todoApp2020.form.CreateTodoForm;
import com.example.ojt.todoApp2020.form.CreateUserForm;
import com.example.ojt.todoApp2020.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoController {

    private final TodoService todoService;
    private final SessionData sessionData;

    @Autowired
    public TodoController(TodoService todoService, SessionData sessionData) {
        this.todoService = todoService;
        this.sessionData = sessionData;
    }

    @ModelAttribute
    CreateTodoForm setupCreateTodoForm() {
        return new CreateTodoForm();
    }

    /**
     * Topページ(ログイン後のページ)
     * @return トップページ
     */
    @RequestMapping("/")
    public String index(@AuthenticationPrincipal OidcUser user, Model model) {
        // sessionにユーザ情報を保存する
        model.addAttribute("user", sessionData.getUser());
        var userList = todoService.findByUserId(sessionData.getUser().getId());
        model.addAttribute("todoList", userList);
        return "index";
    }

    @PostMapping("/create")
    public String create(CreateTodoForm createTodoForm) {
        todoService.createTodoByForm(createTodoForm);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        todoService.delete(id);
        return "redirect:/";
    }
}
