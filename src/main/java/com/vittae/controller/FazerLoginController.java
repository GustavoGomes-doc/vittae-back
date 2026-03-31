	package com.vittae.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vittae.model.FazerLoginDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/login")
public class FazerLoginController {

    @Autowired
    private AuthenticationManager manager;

    
    private SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    @PostMapping
    public ResponseEntity<String> efetuarLogin(FazerLoginDTO dados, HttpServletRequest request, HttpServletResponse response) {
        
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.CPF(), dados.senha());
                
        Authentication authentication = manager.authenticate(authenticationToken);
                
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
               
        securityContextRepository.saveContext(context, request, response);
        
        return ResponseEntity.ok("Login efetuado com sucesso! Sessão criada.");
    }
}