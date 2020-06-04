package com.example.ojt.todoApp2020.controller;

import org.springframework.core.ResolvableType;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    private static final String authorizationRequestBaseUri
        = "oauth2/authorization";
    Map<String, String> oauth2AuthenticationUrls
        = new HashMap<>();

    /**
     * Loginページ
     *
     * @return ログインページ
     */
    @GetMapping("/login")
    public String getLoginPage(Model model) {
        Iterable<ClientRegistration> clientRegistrations = null;
//        ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository)
//            .as(Iterable.class);
//        if (type != ResolvableType.NONE &&
//            ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
//            clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
//        }

//        clientRegistrations.forEach(registration ->
//            oauth2AuthenticationUrls.put(registration.getClientName(),
//                authorizationRequestBaseUri + "/" + registration.getRegistrationId()));
        oauth2AuthenticationUrls.put("facebook",
            authorizationRequestBaseUri + "/" + "facebook");
        oauth2AuthenticationUrls.put("google",
            authorizationRequestBaseUri + "/" + "google");
        model.addAttribute("urls", oauth2AuthenticationUrls);

        return "login";
    }
}
