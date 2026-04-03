package com.vittae.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vittae.model.Exame;
import com.vittae.repository.ExameRepository;

@Service
public class ExameService {

	@Autowired
	private ExameRepository exameRepository;

	public Exame salvar(Exame exame) {
		return exameRepository.save(exame);
	}

	public List<Exame> listarTodos() {
		return exameRepository.findAll();
	}

	public Optional<Exame> buscarPorId(Long id) {
		return exameRepository.findById(id);
	}
	
	public Exame atualizar(Long id, Exame dadosNovos) {
		Exame exame = exameRepository.findById(id).orElseThrow(() -> new RuntimeException("Exame não encontrado"));

		if (dadosNovos.getDescricao() != null)
			exame.setDescricao(dadosNovos.getDescricao());
		
		
		if (dadosNovos.getCid() != 0)
			exame.setCid(dadosNovos.getCid());

		if (dadosNovos.getConsulta() != null)
			exame.setConsulta(dadosNovos.getConsulta());

		return exameRepository.save(exame);
	}

	public void deletar(Long id) {
		exameRepository.deleteById(id);
	}
}