package com.vittae.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.vittae.model.CadastrarUsuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class FazerLoginTokenService {

	@Value("${api.security.token.secret:123456}")
	private String secret;

	public String gerarToken(CadastrarUsuario usuario) {
		try {

			var algoritmo = Algorithm.HMAC256(secret);

			return JWT.create().withIssuer("Vittae")
					  .withSubject(usuario.getEmail())
					  .withExpiresAt(dataExpiracao())
					  .sign(algoritmo);
		} catch (JWTCreationException exception) {
			throw new RuntimeException("Erro ao gerar token JWT", exception);
		}
	}

	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}