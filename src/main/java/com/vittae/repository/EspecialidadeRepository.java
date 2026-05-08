package com.vittae.repository;
 
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vittae.model.Especialidade;
 
@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {
 
    // Busca especialidade pelo nome (ex: "Cardiologia")
    Optional<Especialidade> findByNome(String nome);
}