package com.vittae.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    return http
	        .cors(cors -> cors.configure(http)) // ADICIONE ESTA LINHA: Força o Spring a usar o @CrossOrigin
	        .csrf(csrf -> csrf.disable())
	        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authorizeHttpRequests(req -> {
	            // Permite o GET para medicos
	            req.requestMatchers(HttpMethod.GET, "/api/medicos").permitAll();
	            // Se você tiver uma rota de login, libere-a aqui também
	            req.requestMatchers(HttpMethod.POST, "/api/login").permitAll(); 
	            
	            req.anyRequest().authenticated();
	        })
	        .build();
	}

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}