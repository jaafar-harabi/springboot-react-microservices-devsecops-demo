package com.demo.task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
  @Bean
  SecurityFilterChain security(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(reg -> reg
        .requestMatchers("/actuator/**", "/v3/api-docs/**").permitAll()
        .requestMatchers(HttpMethod.GET, "/api/tasks/**").authenticated()
        .anyRequest().authenticated())
      .oauth2ResourceServer(oauth -> oauth.jwt());
    return http.build();
  }
}
