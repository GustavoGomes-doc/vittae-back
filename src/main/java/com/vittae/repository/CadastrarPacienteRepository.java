package com.vittae.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vittae.model.CadastrarPaciente;

@Repository
public interface CadastrarPacienteRepository extends JpaRepository<CadastrarPaciente, Long> {
}