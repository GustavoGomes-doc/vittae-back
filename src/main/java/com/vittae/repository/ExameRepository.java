package com.vittae.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vittae.model.Exame;

@Repository
public interface ExameRepository extends JpaRepository<Exame, Long> {

}