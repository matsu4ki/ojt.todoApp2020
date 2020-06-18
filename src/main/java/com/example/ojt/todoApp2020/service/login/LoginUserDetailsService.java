package com.example.ojt.todoApp2020.service.login;

import com.example.ojt.todoApp2020.component.LoginUserDetails;
import com.example.ojt.todoApp2020.component.SessionData;
import com.example.ojt.todoApp2020.controller.LoginController;
import com.example.ojt.todoApp2020.entity.User;
import com.example.ojt.todoApp2020.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * フォーム認証時に利用される、UserDetailsServiceをこちらで実装する
 * 実装した場合は、LoginUserDetailsServiceに＠Primaryをつけて、
 * SpringSecurityからこのServiceを優先的に呼んでもらう。
 */
@Primary
@Service
public class LoginUserDetailsService implements UserDetailsService {

    private final SessionData sessionData;
    private final UserService userService;

    @Autowired
    public LoginUserDetailsService(SessionData sessionData, UserService userService) {
        this.sessionData = sessionData;
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // usernameからUserを検索 / ユーザがいない場合はError
        User user = Optional.ofNullable(userService.findByUsername(username))
            .orElseThrow(() -> new UsernameNotFoundException("user not found."));

        // sessionにユーザ情報を保存する
        sessionData.setUser(user);

        // ユーザー情報をSpringSecurityに渡す。
        return new LoginUserDetails(user, getAuthorities(user),null ,null ,null);
    }

    private Collection<GrantedAuthority> getAuthorities(User user) {
        if(user.getRole().equals("ADMIN")) {
            return AuthorityUtils.createAuthorityList("USER", "ADMIN");
        } else {
            return AuthorityUtils.createAuthorityList("USER");
        }
    }

}
