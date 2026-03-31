package com.vittae.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vittae.model.CadastrarUsuario;

@Repository
public interface CadastrarUsuarioRepository extends JpaRepository<CadastrarUsuario, Long> {
}