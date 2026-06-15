package com.vittae.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vittae.dto.PerfilAtualizarDTO;
import com.vittae.model.Medico;
import com.vittae.model.Usuario;
import com.vittae.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

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

    public Usuario atualizar(Long id, Usuario dadosNovos) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        if (dadosNovos.getNome() != null)
            usuario.setNome(dadosNovos.getNome());

        if (dadosNovos.getEmail() != null)
            usuario.setEmail(dadosNovos.getEmail());

        return usuarioRepository.save(usuario);
    }
    
    public Usuario atualizarPerfil(Long id, PerfilAtualizarDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (dto.getEmail() != null && !dto.getEmail().isBlank())
            usuario.setEmail(dto.getEmail());

        if (usuario instanceof Medico medico) {
            if (dto.getTelefone() != null && !dto.getTelefone().isBlank())
                medico.setTelefone(dto.getTelefone());
            if (dto.getValorConsulta() != null)
                medico.setValorConsulta(dto.getValorConsulta());
            if (dto.getTempoConsultaMinutos() != null)
                medico.setTempoConsultaMinutos(dto.getTempoConsultaMinutos());
        }

        return usuarioRepository.save(usuario);
    }

    public void trocarSenha(Long id, PerfilAtualizarDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Valida senha atual
        if (!passwordEncoder.matches(dto.getSenhaAtual(), usuario.getSenha())) {
            throw new RuntimeException("Senha atual incorreta");
        }

        if (dto.getNovaSenha() == null || dto.getNovaSenha().length() < 6) {
            throw new RuntimeException("Nova senha deve ter no mínimo 6 caracteres");
        }

        usuario.setSenha(passwordEncoder.encode(dto.getNovaSenha()));
        usuarioRepository.save(usuario);
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        return usuarioRepository.findByCpf(cpf)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}






