package com.example.ojt.todoApp2020;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        // セキュリティ設定を無視するリクエストの設定
        // 静的リソース(images、css、javascript)を無視する設定を記述
        web.ignoring().antMatchers( "/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // CSRF対策を無効化(デフォルトでは有効化されている)
//        http.csrf().disable();

        // ログイン設定
        http.authorizeRequests()
            .antMatchers("/login").permitAll()
            .anyRequest().authenticated()
            .and()
            .oauth2Login()
            .loginPage("/login");

        // ログアウト設定
        http.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))       // ログアウト処理のパス
            .logoutSuccessUrl("/login")
            // ログアウト時に削除するクッキー名
            .deleteCookies("JSESSIONID")
            // ログアウト時のセッション破棄を有効化
            .invalidateHttpSession(true)
            .permitAll();
    }

}
