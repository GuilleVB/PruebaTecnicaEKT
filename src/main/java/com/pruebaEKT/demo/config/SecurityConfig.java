package com.pruebaEKT.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("admin")
                .password("{noop}admin123")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
