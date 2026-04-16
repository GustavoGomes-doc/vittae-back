package com.vittae.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vittae.model.Medico;

@Repository 
//marca como componente de acesso a dados do spring; 
//permite que o spring decte e registre essa interface no contexto de injecao de dependecnia (igual @autowired no service)
//faz spring traduzir execoes no BD, SQL EXEPTION, DATA ACESS EXEPTION
public interface CadastrarMedicoRepository extends JpaRepository<Medico, Long> { //herda tds os metodos prontos: save, fid by ID, fndall, count etc
	//buscar med por especialidade
	@Query("SELECT m FROM Medico m JOIN m.especialidades e WHERE e.nome = :nome") // quando o spring data não consegue montar a query automaticamente pelo nome do método.
	List<Medico> buscarPorEspecialidade(@Param("nome") String nome);
}