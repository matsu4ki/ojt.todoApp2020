package com.example.ojt.todoApp2020.service.login;

import com.example.ojt.todoApp2020.component.LoginUserDetails;
import com.example.ojt.todoApp2020.component.SessionData;
import com.example.ojt.todoApp2020.entity.User;
import com.example.ojt.todoApp2020.repository.UserRepository;
import com.example.ojt.todoApp2020.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class Oidc2UserService extends OidcUserService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final SessionData sessionData;

    @Autowired
    public Oidc2UserService(UserRepository userRepository,
                            UserService userService,
                            SessionData sessionData) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.sessionData = sessionData;
    }

    @Override
    @Transactional(readOnly = false)
    public OidcUser loadUser(OidcUserRequest userRequest)
        throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        var email = String.valueOf(oidcUser.getEmail());
        var user = userRepository.findByUsername(email);

        if (user == null) {
            user = createNewUser(oidcUser);
            userService.save(user);
            sessionData.setUser(user);
            return oidcUser;
        }

        sessionData.setUser(user);
        GrantedAuthority authority = new OAuth2UserAuthority(user.getRole(), oidcUser.getAttributes());
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(authority);

        return new LoginUserDetails(
            user,
            authorities,
            oidcUser.getAttributes(),
            oidcUser.getIdToken(),
            oidcUser.getUserInfo()
        );
    }

    private User createNewUser(OidcUser oidcUser) {
        var user = new User();
        user.setRole("USER");
        user.setEnabled(true);
        user.setUsername(oidcUser.getEmail());
        return user;
    }
}
