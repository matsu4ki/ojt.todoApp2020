package com.example.ojt.todoApp2020.service.login;

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
 * フォーム認証用LoginUserDetails実装
 * ユーザーネームとEmailで名前を検索する。
 * 見つかった場合、加えてユーザー情報を返却する
 * @author pratula
 *
 */
@Primary
@Service
public class LoginUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // usernameからUserを検索 / ユーザがいない場合はError
        User user = Optional.ofNullable(userService.findByUsername(username))
            .orElseThrow(() -> new UsernameNotFoundException("user not found."));

        // ユーザー情報をSpringSecurityに渡す。
        return new LoginUserDetails(user, getAuthorities(user));
    }

    private Collection<GrantedAuthority> getAuthorities(User user) {
        if(user.getRole().equals("ADMIN")) {
            return AuthorityUtils.createAuthorityList("USER", "ADMIN");
        } else {
            return AuthorityUtils.createAuthorityList("USER");
        }
    }

}
