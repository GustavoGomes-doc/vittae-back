package com.vittae.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vittae.model.CadastrarPaciente;
import com.vittae.repository.CadastrarPacienteRepository;

@Service
public class CadastrarPacienteService {

	@Autowired
	private CadastrarPacienteRepository cadastrarPacienteRepository;

	public CadastrarPaciente salvar(CadastrarPaciente paciente) {
		return cadastrarPacienteRepository.save(paciente);
	}

	public List<CadastrarPaciente> listarTodos() {
		return cadastrarPacienteRepository.findAll();
	}

	public Optional<CadastrarPaciente> buscarPorId(Long id) {
		return cadastrarPacienteRepository.findById(id);
	}
	

	public CadastrarPaciente atualizar(Long id,CadastrarPaciente dadosNovos) {
		CadastrarPaciente paciente = cadastrarPacienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

		if (dadosNovos.getNome() != null)
			paciente.setNome(dadosNovos.getNome());
		
		
		if (dadosNovos.getEmail() != null)
			paciente.setEmail(dadosNovos.getEmail());

		return cadastrarPacienteRepository.save(paciente);
	}

	public void deletar(Long id) {
		cadastrarPacienteRepository.deleteById(id);
	}
}