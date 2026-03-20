package com.vittae.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vittae.model.Medico;
import com.vittae.repository.CadastrarMedicoRepository;

@Service
public class CadastrarMedicoService {

	@Autowired
	private CadastrarMedicoRepository cadastrarMedicoRepository;

	public Medico salvar(Medico medico) {
		return cadastrarMedicoRepository.save(medico);
	}

	public List<Medico> listarTodos() {
		return cadastrarMedicoRepository.findAll();
	}

	public Optional<Medico> buscarPorId(Long id) {
		return cadastrarMedicoRepository.findById(id);
	}
	

	public Medico atualizar(Long id, Medico dadosNovos) {
		Medico medico = cadastrarMedicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Médico não encontrado"));

		if (dadosNovos.getNome() != null)
			medico.setNome(dadosNovos.getNome());
		
		if (dadosNovos.getCrm() != null)
			medico.setCrm(dadosNovos.getCrm());
		
		if (dadosNovos.getEmail() != null)
			medico.setEmail(dadosNovos.getEmail());

		return cadastrarMedicoRepository.save(medico);
	}

	public void deletar(Long id) {
		cadastrarMedicoRepository.deleteById(id);
	}
}