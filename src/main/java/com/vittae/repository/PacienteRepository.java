package com.vittae.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vittae.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
	// Busca o paciente exigindo que o CPF e o Nome sejam iguais
    Optional<Paciente> findByCpfAndNome(String cpf, String nome);
}