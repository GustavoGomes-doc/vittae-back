package com.vittae.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vittae.model.Usuario;
import com.vittae.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository cadastrarUsuarioRepository;

	public Usuario salvar(Usuario usuario) {
		return cadastrarUsuarioRepository.save(usuario);
	}

	public List<Usuario> listarTodos() {
		return cadastrarUsuarioRepository.findAll();
	}

	public Optional<Usuario> buscarPorId(Long id) {
		return cadastrarUsuarioRepository.findById(id);
	}
	

	public Usuario atualizar(Long id,Usuario dadosNovos) {
		Usuario usuario = cadastrarUsuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

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