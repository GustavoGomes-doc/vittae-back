package com.vittae.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.vittae.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class FazerLoginTokenService {

	@Value("${api.security.token.secret:123456}")
	private String secret;

	public String gerarToken(Usuario usuario) {
	    try {
	        var algoritmo = Algorithm.HMAC256(secret);
	        return JWT.create()
	                  .withIssuer("Vittae")
	                  .withSubject(usuario.getCpf())
	                  .withClaim("role", "ROLE_" + usuario.getPerfil().name())
	                  .withExpiresAt(dataExpiracao())
	                  .sign(algoritmo);
	    } catch (JWTCreationException exception) {
	        throw new RuntimeException("Erro ao gerar token JWT", exception);
	    }
	}
	
	public String extrairCpf(String token) {
	    var algoritmo = Algorithm.HMAC256(secret);
	    return JWT.require(algoritmo) // define o algoritmo usado para verificar a assinatura valida que o token foi emitido por "Vittae" (quem criou) -> constrói o verificador -> verifica se o token é válido e não expirou — lança exceção se inválido -> pega o "subject" que você colocou no withSubject() (no seu caso o CPF)
	            .withIssuer("Vittae")
	            .build()
	            .verify(token)
	            .getSubject();
	}
	
	public String extrairRole (String token) {
		var algoritmo = Algorithm.HMAC256(secret);
		return JWT.require(algoritmo)
			.withIssuer("Vittae")
			.build()
			.verify(token)
			.getClaim("role") // pega o claim "role" que você colocou com withClaim("role", ...)
			.asString(); // converte para String (ex: "ROLE_ADMIN")
	} 

	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}