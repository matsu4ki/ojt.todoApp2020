package com.example.ojt.todoApp2020.service.login;

import com.example.ojt.todoApp2020.component.LoginUserDetails;
import com.example.ojt.todoApp2020.component.SessionData;
import com.example.ojt.todoApp2020.entity.User;
import com.example.ojt.todoApp2020.repository.UserRepository;
import com.example.ojt.todoApp2020.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final SessionData sessionData;

    @Autowired
    public OAuth2UserService(UserRepository userRepository,
                             UserService userService,
                             SessionData sessionData) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.sessionData = sessionData;
    }

    /*
     * NOTE: Facebookの前提としている
     */
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        var email = String.valueOf(oAuth2User.getAttributes().get("email"));
        var user = userRepository.findByUsername(email);

        if (user == null) {
            user = createNewFaceBookUser(oAuth2User);
            userService.save(user);
            sessionData.setUser(user);
            return oAuth2User;
        }

        sessionData.setUser(user);
        GrantedAuthority authority = new OAuth2UserAuthority(user.getRole(), oAuth2User.getAttributes());
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(authority);

        return new LoginUserDetails(
            user,
            authorities,
            oAuth2User.getAttributes(),
            null,
            null);
    }

    private User createNewFaceBookUser(OAuth2User oAuth2User) {
        var user = new User();
        user.setRole("USER");
        user.setEnabled(true);
        user.setUsername((String)oAuth2User.getAttributes().get("email"));
        return user;
    }
}
