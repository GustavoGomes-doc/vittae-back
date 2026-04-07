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
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    return http
	        .csrf(csrf -> csrf.disable())
	        .cors(Customizer.withDefaults()) // Use a configuração padrão que respeita o @CrossOrigin
	        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authorizeHttpRequests(req -> {
	            req.requestMatchers(HttpMethod.GET, "/api/medicos").permitAll();
	            req.requestMatchers(HttpMethod.POST, "/api/login").permitAll(); 
	            
	            // ADICIONE ESTA LINHA ABAIXO PARA LIBERAR O AGENDAMENTO:
	            req.requestMatchers(HttpMethod.POST, "/api/agendamentos").permitAll(); 
	            
	            req.anyRequest().authenticated();
	        })
	        .build();
	}

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}