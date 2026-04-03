package com.vittae.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vittae.model.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}