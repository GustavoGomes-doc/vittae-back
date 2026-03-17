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
}