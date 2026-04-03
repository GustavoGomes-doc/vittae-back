package com.vittae.service;

import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vittae.model.Consulta;
import com.vittae.repository.ConsultaRepository;

@Service
public class ConsultaService {

	@Autowired
	private ConsultaRepository consultaRepository;

	public Consulta salvar(Consulta consulta) {
		return consultaRepository.save(consulta);
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
