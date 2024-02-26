package com.example.asociaciones.security;

import com.example.asociaciones.security.filter.JwtAuthenticationFilter;
import com.example.asociaciones.security.filter.JwtValidationFilter;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/users/{username}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/users/register").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/users/register").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/canciones").hasAnyRole("ADMIN", "ARTISTA")
                        .requestMatchers(HttpMethod.GET, "/api/canciones", "/api/canciones/{id}", "/api/canciones/betweenDates", "/api/canciones/nombre/{nombre}", "/api/canciones/genero/{Nombre}", "/api/canciones/fechaReciente").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/canciones/{id}").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/canciones/{id}").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/generos").hasAnyRole("ADMIN", "ARTISTA")
                        .requestMatchers(HttpMethod.GET, "/api/generos", "/api/generos/{id}", "/api/generos/nombre/{Nombre}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/generos/{id}").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/generos/{id}").hasAnyRole("ADMIN")
                        .anyRequest().authenticated())
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationManager()))
                .csrf(config -> config.disable())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();

    }
}
