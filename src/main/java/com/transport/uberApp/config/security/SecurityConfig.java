package com.transport.uberApp.config.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final String[] AUTHENTICATION_WHITE_LIST = {"/api/v1/driver/register", "/api/v1/passenger"};
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        UsernamePasswordAuthenticationFilter authenticationFilter = new UberAppAuthenticationFilter(authenticationManager, jwtUtil);
        authenticationFilter.setFilterProcessesUrl("/api/v1/login");
        return http.csrf().disable().cors().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new UberAppAuthorizationFilter(jwtUtil), UberAppAuthenticationFilter.class)
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, AUTHENTICATION_WHITE_LIST)
                .permitAll()
                .and()
                .authorizeHttpRequests().anyRequest().authenticated()
                .and()
                .build();
    }
}
