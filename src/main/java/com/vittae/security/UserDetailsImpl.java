package com.vittae.security; // Crie um pacote separado para segurança

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.vittae.model.Usuario;

public class UserDetailsImpl implements UserDetails {

    // Guarda o usuário original do banco de dados
    private final Usuario usuario;

    public UserDetailsImpl(Usuario usuario) {
        this.usuario = usuario;
    }
    
    

    // O Spring pergunta: "Quais as permissões dele?"
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Aqui você converte o Perfil (ADMIN, MEDICO, etc) para a permissão do Spring
        return List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getPerfil().name()));
    }

    // O Spring pergunta: "Qual a senha?"
    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    // O Spring pergunta: "Qual o login?" (no seu caso, é o CPF)
    @Override
    public String getUsername() {
        return usuario.getCpf();
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
    
    // Método extra útil para pegar o usuário original se precisar
    public Usuario getUsuario() {
        return this.usuario;
    }
}