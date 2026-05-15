package com.cognizant.ems.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Configure Request Authorization
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/css/**", "/js/**", "/api/**").permitAll() // Open APIs and Login
                // Enforce ADMIN role on critical operations
                .requestMatchers("/showNewEmployeeForm", "/saveEmployee", "/showFormForUpdate/**", "/deleteEmployee/**").hasRole("ADMIN")
                // General authenticated access for any other endpoints (like Dashboard)
                .anyRequest().authenticated()
            )
            // Configure Form Based Login
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/authenticateUser")
                .defaultSuccessUrl("/", true) // Force redirect to dashboard on success
                .permitAll()
            )
            // Configure Logout
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        // Required for Postman/API JSON testing locally otherwise POST /api/employees will be blocked internally
        http.csrf(csrf -> csrf.disable()); 

        return http.build();
    }
}
