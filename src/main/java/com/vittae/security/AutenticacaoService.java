package com.vittae.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vittae.model.Usuario;
import com.vittae.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

    private final UsuarioRepository repository;

    // Injeção de dependência via construtor (Boa prática do mundo real)
    public AutenticacaoService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        // 1. Vai no banco de dados e tenta achar o usuário pelo CPF
        Usuario usuario = repository.findByCpf(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o CPF: " + username));

        // 2. Achou o usuário! Agora embrulhamos ele no nosso Adaptador e devolvemos pro Spring
        return new UserDetailsImpl(usuario);
    }
}