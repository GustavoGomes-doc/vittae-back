package com.vittae.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.vittae.model.Paciente;
import com.vittae.dto.PacienteAdminDTO;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByCpfAndNome(String cpf, String nome);
    Optional<Paciente> findByCpf(String cpf); // FIX: adicionado para buscar responsável
    @Query("""
        SELECT p FROM Paciente p LEFT JOIN FETCH p.consultas
    """)
    List<Paciente> findAllComConsultas();
}