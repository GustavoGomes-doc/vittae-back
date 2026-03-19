package com.vittae.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vittae.model.Medico;
import com.vittae.repository.CadastrarMedicoRepository;

@Service
public class CadastrarMedicoService {

	@Autowired
	private CadastrarMedicoService cadastrarMedicoService;

	private CadastrarMedicoRepository cadastrarMedicoRepository;

	public Medico salvar(Medico medico) {
		return cadastrarMedicoRepository.save(medico);
	}

	public List<Medico> listarTodos() {
		return repository.findAll();
	}

	public Optional<Medico> buscarPorId(Long id) {
		return repository.findById(id);
	}

	public Medico atualizar(Long id, Medico dadosNovos) {
		Medico medico = repository.findById(id).orElseThrow(() -> new RuntimeException("Médico não encontrado"));

		if (dadosNovos.getNome() != null)
			medico.setNome(dadosNovos.getNome());
		
		if (dadosNovos.getCrm() != null)
			medico.setCrm(dadosNovos.getCrm());
		
		if (dadosNovos.getEspecialidade() != null)
			medico.setEspecialidade(dadosNovos.getEspecialidade());
		
		if (dadosNovos.getEmail() != null)
			medico.setEmail(dadosNovos.getEmail());

		return repository.save(medico);
	}

	public void deletar(Long id) {
		repository.deleteById(id);
	}
}