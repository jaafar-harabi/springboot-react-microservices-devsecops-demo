package com.demo.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
  @Bean
  SecurityFilterChain appSecurity(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests(reg -> reg
        .requestMatchers("/actuator/**").permitAll()
        .anyRequest().authenticated())
      .formLogin(Customizer.withDefaults());
    return http.build();
  }
}
