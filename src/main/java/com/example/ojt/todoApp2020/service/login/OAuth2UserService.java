package com.example.ojt.todoApp2020.service.login;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

    public void saveOAuthUser(OidcUser user) {
//        user.getAttributes().entrySet().forEach(claim -> {
//            claim.getKey()
//        });        user.getAttributes().entrySet().forEach(claim -> {
//            claim.getKey()
//        });
    }
}
