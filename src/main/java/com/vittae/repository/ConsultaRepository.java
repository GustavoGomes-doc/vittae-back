package com.vittae.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vittae.model.Consulta;
import com.vittae.model.CadastrarUsuario; // Certifique-se que este import existe

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    // Se tiver métodos de consulta aqui, pode manter
}

@Repository
interface CadastrarUsuarioRepository extends JpaRepository<CadastrarUsuario, Long> {
    Optional<CadastrarUsuario> findByCpf(String cpf);
}