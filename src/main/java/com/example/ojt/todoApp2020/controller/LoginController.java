package com.example.ojt.todoApp2020.controller;

import com.example.ojt.todoApp2020.form.CreateUserForm;
import com.example.ojt.todoApp2020.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @ModelAttribute
    CreateUserForm setupCreateUserForm() {
        return new CreateUserForm();
    }

    private static final String authorizationRequestBaseUri = "oauth2/authorization";
    Map<String, String> oauth2AuthenticationUrls = new HashMap<>();

    @GetMapping("/login")
    public String loginPage(Model model) {
        oauth2AuthenticationUrls.put("facebook", authorizationRequestBaseUri + "/" + "facebook");
        oauth2AuthenticationUrls.put("google", authorizationRequestBaseUri + "/" + "google");
        model.addAttribute("urls", oauth2AuthenticationUrls);
        return "login";
    }

    @GetMapping("/signup")
    public String signUpPage(Model model) {
        return "signup";
    }

    @PostMapping("/signup/createuser")
    public String createUser(@ModelAttribute("createUserForm")CreateUserForm createUserForm, RedirectAttributes redirectAttributes) {
        userService.createUserByForm(createUserForm);
        redirectAttributes.addFlashAttribute("message", "登録が完了しました！");
        return "redirect:/login";
    }
}
