package com.vittae.security;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .cors(Customizer.withDefaults())
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(req -> {
                req.requestMatchers(HttpMethod.POST, "/api/login").permitAll();
                req.requestMatchers(HttpMethod.POST, "/api/usuarios/cadastrar").permitAll();
                req.requestMatchers(HttpMethod.GET,  "/api/especialidades").permitAll();
                req.requestMatchers(HttpMethod.GET,  "/api/medicos").permitAll();
                req.requestMatchers(HttpMethod.GET,  "/api/medicos/*/horarios-livres").permitAll();
                req.requestMatchers(HttpMethod.PUT, "/api/usuarios/**").authenticated();
                req.requestMatchers(HttpMethod.PUT, "/api/usuarios/*/perfil").authenticated();
                req.requestMatchers(HttpMethod.PUT, "/api/usuarios/*/senha").authenticated();
                req.requestMatchers(HttpMethod.PUT, "/api/usuarios/{id}/perfil").authenticated();
                

                req.requestMatchers(HttpMethod.POST, "/api/medicos").hasRole("ADMIN");
                req.requestMatchers(HttpMethod.POST, "/api/especialidades").hasRole("ADMIN");
                req.requestMatchers(HttpMethod.GET, "/api/agendamentos/todos").hasRole("ADMIN");
                req.requestMatchers(HttpMethod.GET, "/api/agendamentos/paciente/**").hasRole("ADMIN");
                req.requestMatchers(HttpMethod.GET, "/api/usuarios/pacientes").hasRole("ADMIN");
                req.requestMatchers(HttpMethod.DELETE, "/api/medicos/**").hasRole("ADMIN");
                
                req.requestMatchers(HttpMethod.GET, "/api/consultas/**").hasAnyRole("MEDICO", "ADMIN");
                req.requestMatchers(HttpMethod.GET, "/api/disponibilidade/**").hasAnyRole("MEDICO", "ADMIN");
                req.requestMatchers(HttpMethod.GET, "/api/agendamentos/medico/**").hasAnyRole("MEDICO", "ADMIN");
                req.requestMatchers(HttpMethod.GET,    "/api/disponibilidade/**").hasAnyRole("MEDICO", "ADMIN");
                req.requestMatchers(HttpMethod.GET,    "/api/agendamentos/medico/**").hasAnyRole("MEDICO", "ADMIN");
                
                req.requestMatchers(HttpMethod.POST,   "/api/disponibilidade/**").hasRole("MEDICO");
                req.requestMatchers(HttpMethod.DELETE, "/api/disponibilidade/**").hasRole("MEDICO");
           
                req.requestMatchers(HttpMethod.POST, "/api/agendamentos").hasRole("PACIENTE");
                req.requestMatchers(HttpMethod.GET,  "/api/agendamentos").hasRole("PACIENTE");
                req.requestMatchers(HttpMethod.PUT,  "/api/agendamentos/**").hasRole("PACIENTE");

                req.anyRequest().authenticated();
            })
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

     @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        
        // 1. Mudamos de setAllowedOrigins para setAllowedOriginPatterns usando "*"
        // Isso diz ao Spring para aceitar qualquer origem de forma dinâmica e segura
        config.setAllowedOriginPatterns(List.of("*"));
        
        // Mantemos os seus métodos permitidos
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
        
        // Mantemos a liberação de todos os cabeçalhos (essencial para o JwtAuthFilter ler o Token)
        config.setAllowedHeaders(List.of("*"));
        
        // 2. Liberamos o envio de credenciais/cookies de autenticação entre o Front e o Back
        config.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
} 
