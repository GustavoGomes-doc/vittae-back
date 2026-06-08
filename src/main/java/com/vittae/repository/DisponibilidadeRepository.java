package com.vittae.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vittae.model.Disponibilidade;

public interface DisponibilidadeRepository extends JpaRepository<Disponibilidade, Long> {
	
}