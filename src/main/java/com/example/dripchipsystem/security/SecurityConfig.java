package com.example.dripchipsystem.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests(auth -> {
                    auth.expressionHandler(webExpressionHandler());
                    auth.antMatchers("/registration").anonymous();
                    auth.antMatchers(HttpMethod.GET, "/accounts/search").hasAuthority("ADMIN");
                    auth.antMatchers(HttpMethod.GET, "/accounts/{userId}")
                            .access("@securityMethods.hasUserId(authentication, #userId) or hasAuthority('ADMIN')");
                    auth.antMatchers(HttpMethod.DELETE, "/accounts/{userId}")
                            .access("@securityMethods.hasUserId(authentication, #userId) or hasAuthority('ADMIN')");
                    auth.antMatchers(HttpMethod.PUT, "/accounts/{userId}")
                            .access("@securityMethods.hasUserId(authentication, #userId) or hasAuthority('ADMIN')");
                    auth.antMatchers(HttpMethod.POST, "/accounts").hasAuthority("ADMIN");
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

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_CHIPPER\nROLE_CHIPPER > ROLE_USER");
        return roleHierarchy;
    }

    @Bean
    public SecurityExpressionHandler<FilterInvocation> webExpressionHandler() {
        DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
        defaultWebSecurityExpressionHandler.setRoleHierarchy(roleHierarchy());
        return defaultWebSecurityExpressionHandler;
    }
}
