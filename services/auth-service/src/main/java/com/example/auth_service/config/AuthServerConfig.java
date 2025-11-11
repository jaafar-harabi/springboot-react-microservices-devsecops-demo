package com.demo.auth.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.authorization.InMemoryOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.InMemoryOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthServerConfig {

  @Bean
  SecurityFilterChain asSecurityFilterChain(HttpSecurity http) throws Exception {
    OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
    return http.formLogin(Customizer.withDefaults()).build();
  }

  @Bean
  RegisteredClientRepository registeredClientRepository() {
    RegisteredClient rc = RegisteredClient
        .withId(UUID.randomUUID().toString())
        .clientId("react-client")
        .clientSecret("{noop}change-me") // for confidential clients; PKCE public clients do not need secret
        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
        .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
        .redirectUri("http://localhost:5173/login/callback")
        .scope("openid")
        .scope("profile")
        .scope("tasks.read")
        .scope("tasks.write")
        .build();
    return new InMemoryRegisteredClientRepository(rc);
  }

  @Bean
  OAuth2AuthorizationService authorizationService() {
    return new InMemoryOAuth2AuthorizationService();
  }

  @Bean
  OAuth2AuthorizationConsentService authorizationConsentService() {
    return new InMemoryOAuth2AuthorizationConsentService();
  }

  // Generate ephemeral RSA JWK for dev; replace with persistent JWK/JKS in prod
  @Bean
  JWKSet jwkSet() throws Exception {
    KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
    kpg.initialize(2048);
    KeyPair kp = kpg.generateKeyPair();
    RSAKey rsa = new RSAKey.Builder((RSAPublicKey) kp.getPublic())
        .privateKey((RSAPrivateKey) kp.getPrivate())
        .keyID(UUID.randomUUID().toString())
        .build();
    return new JWKSet(rsa);
  }

  @Bean
  JwtDecoder jwtDecoder(JWKSet jwkSet) {
    return NimbusJwtDecoder.withJwkSetUri("/oauth2/jwks").jwkSet(jwkSet).build();
  }
}
