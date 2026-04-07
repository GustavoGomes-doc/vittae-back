package com.vittae.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vittae.model.Paciente;
import com.vittae.repository.PacienteRepository;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository PacienteRepository;

	public Paciente salvar(Paciente paciente) {
		return PacienteRepository.save(paciente);
	}

	public List<Paciente> listarTodos() {
		return PacienteRepository.findAll();
	}

	public Optional<Paciente> buscarPorId(Long id) {
		return PacienteRepository.findById(id);
	}
	

	public Paciente atualizar(Long id,Paciente dadosNovos) {
		Paciente paciente = PacienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

		if (dadosNovos.getNome() != null)
			paciente.setNome(dadosNovos.getNome());
		
		
		if (dadosNovos.getEmail() != null)
			paciente.setEmail(dadosNovos.getEmail());

		return PacienteRepository.save(paciente);
	}

	public void deletar(Long id) {
		PacienteRepository.deleteById(id);
	}
}