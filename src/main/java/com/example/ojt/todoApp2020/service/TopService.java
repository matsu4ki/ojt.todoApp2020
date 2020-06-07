package com.example.ojt.todoApp2020.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class TopService {

    @Autowired
    public TopService() {

    }

    public void saveOIDCUser(OidcUser user) {
//        user.getAttributes().entrySet().forEach(claim -> {
//            claim.getKey()
//        });
    }
}
