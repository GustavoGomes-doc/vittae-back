package com.vittae.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.vittae.model.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    
    List<Consulta> findByPacienteCpf(String cpf);
    
    @Query("SELECT c.hora FROM Consulta c WHERE c.medico.id = :medicoId AND c.dataConsulta = :data AND c.status <> 'CANCELADA'")
    List<LocalTime> findHorariosOcupados(@Param("medicoId") Long medicoId, @Param("data") LocalDate data);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Consulta c WHERE c.medico.id = :medicoId AND c.dataConsulta = :data AND c.hora = :hora AND c.status <> 'CANCELADA'")
    boolean existsConsultaOcupada(@Param("medicoId") Long medicoId, @Param("data") LocalDate data, @Param("hora") LocalTime hora);
    
    @Query("SELECT c FROM Consulta c LEFT JOIN FETCH c.paciente LEFT JOIN FETCH c.medico")
    List<Consulta> findAllComRelacionamentos();
    
    List<Consulta> findByMedicoId(Long medicoId);
}