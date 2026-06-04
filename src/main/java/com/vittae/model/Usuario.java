package com.vittae.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.vittae.model.enums.Perfil;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;
    private String senha;
    private String nome;
    private String email;

    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    public Usuario() {}

    public Usuario(String cpf, String senha, String nome, String email) {
        this.cpf = cpf;
        this.senha = senha;
        this.nome = nome;
        this.email = email;
    }

 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return List.of(); }

    @Override
    public String getPassword() { return this.senha; }

    @Override
    public String getUsername() { return this.cpf; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}