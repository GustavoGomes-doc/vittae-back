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
public class UsuarioService {
	
	@Autowired 
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Usuario salvar(Usuario usuario) {
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		return usuarioRepository.save(usuario);
	}

	public List<Usuario> listarTodos() {
		return usuarioRepository.findAll();
	}

	public Optional<Usuario> buscarPorId(Long id) {
		return usuarioRepository.findById(id);
	}
	

	public Usuario atualizar(Long id,Usuario dadosNovos) {
		Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

		if (dadosNovos.getNome() != null)
			usuario.setNome(dadosNovos.getNome());
		
		
		if (dadosNovos.getEmail() != null)
			usuario.setEmail(dadosNovos.getEmail());

		return usuarioRepository.save(usuario);
	}

	public void deletar(Long id) {
		usuarioRepository.deleteById(id);
	}
	
	//public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
	    
	    // 1. Busca o usuário puro no banco
	    //Usuario usuario = usuarioRepository.findByCpf(cpf)
	      //      .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
	            
	    // 2. Embrulha o usuário no adaptador e devolve pro Spring Security
	   // return new UserDetailsImpl(usuario);
	//}
}





