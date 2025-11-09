@Configuration @EnableWebFluxSecurity
class SecurityConfig {
  @Bean SecurityWebFilterChain chain(ServerHttpSecurity http){
    return http.csrf(ServerHttpSecurity.CsrfSpec::disable)
      .authorizeExchange(ex -> ex.pathMatchers("/auth/**","/actuator/**").permitAll().anyExchange().authenticated())
      .oauth2ResourceServer(o -> o.jwt()).build();
  }
}
