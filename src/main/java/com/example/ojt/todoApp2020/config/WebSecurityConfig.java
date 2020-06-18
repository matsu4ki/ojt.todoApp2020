package com.example.ojt.todoApp2020.config;

import com.example.ojt.todoApp2020.service.login.LoginUserDetailsService;
import com.example.ojt.todoApp2020.service.login.OAuth2UserService;
import com.example.ojt.todoApp2020.service.login.Oidc2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final LoginUserDetailsService loginUserDetailsService;

    private final BCryptPasswordEncoder passwordEncoder;

    private final OAuth2UserService oAuth2UserService;

    private final Oidc2UserService oidc2UserService;

    @Autowired
    public WebSecurityConfig(LoginUserDetailsService loginUserDetailsService,
                             BCryptPasswordEncoder bCryptPasswordEncoder,
                             OAuth2UserService oAuth2UserService,
                             Oidc2UserService oidc2UserService) {
        this.loginUserDetailsService = loginUserDetailsService;
        this.passwordEncoder = bCryptPasswordEncoder;
        this.oAuth2UserService = oAuth2UserService;
        this.oidc2UserService = oidc2UserService;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // セキュリティ設定を無視するリクエストの設定
        // 静的リソース(images、css、javascriptなど)を無視する設定を記述
        web.ignoring().antMatchers( "/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // CSRF対策を無効化(デフォルトでは有効化されている)
//        http.csrf().disable();

        // ログイン全般の設定
//        ConcurrencyControlConfigurer


        http.authorizeRequests()
            .antMatchers("/login", "/signup", "/signup/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .sessionManagement().invalidSessionUrl("/login");

        // OAuth2を利用したログイン
        http.oauth2Login()
            .loginPage("/login")
            .defaultSuccessUrl("/")
            .userInfoEndpoint()
            // OAuth認証時に実行するServiceクラス
            .userService(oAuth2UserService)
            // OpenId認証時に実行するServiceクラス
            .oidcUserService(oidc2UserService);

        // フォームを利用したローカルのログイン
        http.formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/signin") //フォームのSubmitURL、このURLへリクエストが送られると認証処理が実行される
            .usernameParameter("username")
            .passwordParameter("password")
            .defaultSuccessUrl("/")
            .failureUrl("/login")
            .permitAll();

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

    /**
     * UserDetailsService、passwordEncoderをセットする
     */
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginUserDetailsService)
            .passwordEncoder(passwordEncoder);
    }
}
