package com.example.ojt.todoApp2020.controller;

import com.example.ojt.todoApp2020.service.TopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TopController {

    private final TopService topService;

    @Autowired
    public TopController(TopService topService) {
        this.topService = topService;
    }

    /**
     * Topページ
     * @return トップページ
     */
    @RequestMapping("/")
    public String index(@AuthenticationPrincipal OidcUser user, Model model) {
        topService.saveOIDCUser(user);
        return "index";
    }


    /**
     * ログイン後のページ
     * @return Todoのメインページ
     */
    @RequestMapping("/hoge")
    public String hoge(@AuthenticationPrincipal OidcUser user, Model model) {
        model.addAttribute("username", user.getFullName());
        return "todo";
    }
}
