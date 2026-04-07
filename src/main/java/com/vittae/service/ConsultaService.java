package com.vittae.service;

import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vittae.dto.AgendamentoDTO;
import com.vittae.model.Consulta;
import com.vittae.repository.ConsultaRepository;

@Service
public class ConsultaService {

	@Autowired
	private ConsultaRepository consultaRepository;

	public void salvarAgendamento(AgendamentoDTO dto) {
	    // 1. Cria uma nova instância da sua entidade
	    Consulta novaConsulta = new Consulta();
	    
	    // 2. Mapeia os dados do DTO para os campos que vi no seu print
	    novaConsulta.setDataAgendado(dto.getDataAgendado()); 
	    novaConsulta.setDataConsulta(dto.getDataConsulta());
	    novaConsulta.setHora(dto.getHora());
	    novaConsulta.setValorconsulta(dto.getDataConsulta());
	    novaConsulta.setMedicoId(dto.getMedicoId());
	    novaConsulta.setPacienteId(dto.getPacienteId());
	    
	
	    
	    // 3. O comando que realiza o INSERT no banco de dados
	    consultaRepository.save(novaConsulta);
	}

	public List<Consulta> listarTodos() {
		return consultaRepository.findAll();
	}

	public Optional<Consulta> buscarPorId(Long id) {
		return consultaRepository.findById(id);
	}

	public @Nullable Object atualizar(Long id, Consulta consulta) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void deletar(Long id) {
		// TODO Auto-generated method stub
		
	}
}
