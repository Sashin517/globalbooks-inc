package com.globalbooks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // 1. Configure the HTTP Security rules
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF for REST APIs
            .authorizeRequests()
            .antMatchers("/orders/**").authenticated() // Protect the /orders endpoint
            .anyRequest().permitAll()
            .and()
            .httpBasic(); // Use Basic Authentication (Username & Password)
        return http.build();
    }

    // 2. Create a hardcoded User for testing purposes
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("secret123")
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
}