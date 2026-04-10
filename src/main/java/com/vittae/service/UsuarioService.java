package com.vittae.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vittae.model.Usuario;
import com.vittae.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository UsuarioRepository;
	private PasswordEncoder passwordEncoder;
	
	public Usuario salvar(Usuario usuario) {
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
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
	
	@Override
	public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
	    return UsuarioRepository.findByCpf(cpf)
	        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
	}
}





