package com.example.dripchipsystem.security;

import com.example.dripchipsystem.account.dto.AccountDto;
import com.example.dripchipsystem.account.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.server.ResponseStatusException;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private AccountService accountService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests(auth -> {
                    auth.antMatchers("/registration").anonymous();
                    auth.antMatchers(HttpMethod.GET).access("isAnonymous() || isAuthenticated()");
                    auth.antMatchers(HttpMethod.DELETE, "/accounts/{userId}")
                            .access("@securityConfig.hasUserId(authentication, #userId)");
                    auth.antMatchers(HttpMethod.PUT, "/accounts/{userId}")
                            .access("@securityConfig.hasUserId(authentication, #userId)");
                    auth.anyRequest().authenticated();
                })
                .httpBasic();
        return http.build();
    }

    @Bean
    public String AuthorizationHeader() {
        return "Authorization";
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    public boolean hasUserId(Authentication authentication, Long userId) {
        if (userId <= 0) return true;
        String email = authentication.getName();
        AccountDto dto;
        try {
            dto = accountService.getEntity(userId);
        } catch (ResponseStatusException ex) {
            return false;
        }
        return email.equals(dto.getEmail());
    }
}
