package com.example.ojt.todoApp2020.component;

import com.example.ojt.todoApp2020.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

/**
 * ログイン認証に必要なクラス
 * Form,OAuth2, OIdCのどれでログインする場合も、このオブジェクトを生成する。
 * ログイン間の差異をこのDataオブジェクトで吸収した後、SpringSecurityに返している。
 * 各ログインでの参考として、Default
 * org.springframework.security.core.userdetails.User が本家
 */
public class LoginUserDetails implements UserDetails, OAuth2User, OidcUser {

    private final User user;
    private final Collection<GrantedAuthority> authorities;
    private final Map<String, Object> attributes;
    private final OidcIdToken idToken;
    private final OidcUserInfo userInfo;

    public LoginUserDetails(
        User user,
        Collection<GrantedAuthority> authorities,
        Map<String, Object> attributes,
        OidcIdToken idToken,
        OidcUserInfo userInfo) {
        this.user = user;
        this.attributes = attributes;
        this.authorities = authorities;
        this.idToken = idToken;
        this.userInfo = userInfo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isEnabled();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    @Override
    public Map<String, Object> getClaims() {
        return this.attributes;
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return userInfo;
    }

    @Override
    public OidcIdToken getIdToken() {
        return idToken;
    }

    @Override
    public String getName() {
        if (user.getUsername() != null) {
            return user.getUsername();
        } else if (attributes.get("email") != null) {
            return String.valueOf(attributes.get("email"));
        } else if (userInfo.getEmail() != null) {
            return userInfo.getEmail();
        } else {
            return null;
        }
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }
}
