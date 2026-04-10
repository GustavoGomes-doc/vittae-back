package com.vittae.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vittae.model.Medico;

@Repository
public interface CadastrarMedicoRepository extends JpaRepository<Medico, Long> {
}