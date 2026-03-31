package com.vittae.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vittae.model.CadastrarUsuario;
import com.vittae.repository.CadastrarUsuarioRepository;

@Service
public class CadastrarUsuarioService {

	@Autowired
	private CadastrarUsuarioRepository cadastrarUsuarioRepository;

	public CadastrarUsuario salvar(CadastrarUsuario usuario) {
		return cadastrarUsuarioRepository.save(usuario);
	}

	public List<CadastrarUsuario> listarTodos() {
		return cadastrarUsuarioRepository.findAll();
	}

	public Optional<CadastrarUsuario> buscarPorId(Long id) {
		return cadastrarUsuarioRepository.findById(id);
	}
	

	public CadastrarUsuario atualizar(Long id,CadastrarUsuario dadosNovos) {
		CadastrarUsuario usuario = cadastrarUsuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

		if (dadosNovos.getNome() != null)
			usuario.setNome(dadosNovos.getNome());
		
		
		if (dadosNovos.getEmail() != null)
			usuario.setEmail(dadosNovos.getEmail());

		return cadastrarUsuarioRepository.save(usuario);
	}

	public void deletar(Long id) {
		cadastrarUsuarioRepository.deleteById(id);
	}
}