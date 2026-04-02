package com.vittae.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vittae.model.FazerLoginDTO;
import com.vittae.model.TokenResponseDTO;
import com.vittae.service.FazerLoginTokenService;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "*")
public class FazerLoginController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private FazerLoginTokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenResponseDTO> efetuarLogin(@RequestBody FazerLoginDTO dados) {
        
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.CPF(), dados.senha());
                
        Authentication authentication = manager.authenticate(authenticationToken);
                
        // Quando você for ligar o seu Service de verdade, é só descomentar a linha abaixo:
        // String tokenJWT = tokenService.gerarToken(authentication);
        
        return ResponseEntity.ok(new TokenResponseDTO("token_gerado_com_sucesso"));
    }
}