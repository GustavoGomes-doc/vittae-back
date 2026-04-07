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
	private UsuarioRepository UsuarioRepository;

	public Usuario salvar(Usuario usuario) {
		return UsuarioRepository.save(usuario);
	}

	public List<Usuario> listarTodos() {
		return UsuarioRepository.findAll();
	}

	public Optional<Usuario> buscarPorId(Long id) {
		return UsuarioRepository.findById(id);
	}
	

	public Usuario atualizar(Long id,Usuario dadosNovos) {
		Usuario usuario = UsuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

		if (dadosNovos.getNome() != null)
			usuario.setNome(dadosNovos.getNome());
		
		
		if (dadosNovos.getEmail() != null)
			usuario.setEmail(dadosNovos.getEmail());

		return UsuarioRepository.save(usuario);
	}

	public void deletar(Long id) {
		UsuarioRepository.deleteById(id);
	}
}