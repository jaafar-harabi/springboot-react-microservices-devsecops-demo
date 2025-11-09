@Configuration
class SecurityConfig {
  @Bean SecurityFilterChain f(HttpSecurity http) throws Exception {
    http.csrf(csrf->csrf.disable())
        .authorizeHttpRequests(reg->reg.requestMatchers("/auth/**","/actuator/**").permitAll().anyRequest().authenticated())
        .httpBasic(b->{});
    return http.build();
  }
  @Bean PasswordEncoder encoder(){ return new BCryptPasswordEncoder(); }
}
