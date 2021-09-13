package com.example.userservice.security;

import com.example.userservice.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;


@Configuration
@EnableWebSecurity
@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*")
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private Environment env;

    public WebSecurity(Environment env, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.env = env;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

/* 리소스들 권한 설정*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
//        http.authorizeRequests().antMatchers("/users/**").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();
        http.authorizeRequests().antMatchers("/health_check/**").permitAll();
        http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
//        http.authorizeRequests().antMatchers("/actuator/**").permitAll();
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
//                .hasIpAddress(env.getProperty("gateway.ip"))
<<<<<<< Updated upstream
<<<<<<< HEAD
<<<<<<< Updated upstream
                .access("hasIpAddress('172.18.0.5') or hasIpAddress('192.168.35.111') or hasIpAddress('127.0.0.1')")
=======
                .access("hasIpAddress('172.18.0.5') or hasIpAddress('192.168.0.4') or hasIpAddress('127.0.0.1')")
>>>>>>> Stashed changes
=======
//                .access("@authorizationChecker.check(request, authentication)")
>>>>>>> 308d72e012ab539beade09a33c416191ad380ff5
=======
                .access("hasIpAddress('172.18.0.5') or hasIpAddress('192.168.0.4') or hasIpAddress('127.0.0.1')")
>>>>>>> Stashed changes
                .and()
                .addFilter(getAuthenticationFilter());
        ;
        http.headers().frameOptions().disable();
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(
                authenticationManager(), userService, env);

        return authenticationFilter;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }
}