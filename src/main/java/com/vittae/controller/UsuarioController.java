package com.vittae.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vittae.dto.PerfilAtualizarDTO;
import com.vittae.model.Usuario;
import com.vittae.repository.UsuarioRepository;
import com.vittae.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

	@Autowired
	private UsuarioService UsuarioService;
	private UsuarioRepository UsuarioRepository;

	@PostMapping("/cadastrar") 
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        Usuario usuarioSalvo = UsuarioService.salvar(usuario);
        return ResponseEntity.ok(usuarioSalvo);
    }
	
	@PutMapping("/{id}/perfil")
	public ResponseEntity<?> atualizarPerfil(@PathVariable Long id,
											@RequestBody PerfilAtualizarDTO dto) {
		try {
			Usuario atualizado = UsuarioService.atualizarPerfil(id, dto);
			return ResponseEntity.ok(atualizado);
			} 	catch (RuntimeException e) {
					return ResponseEntity.badRequest().body(e.getMessage());			
				}
		
	}
	
	@PutMapping("{id}/senha")
	public ResponseEntity<?> trocarSenha(@PathVariable Long id,
										@RequestBody PerfilAtualizarDTO dto) {
		try {
			UsuarioService.trocarSenha(id, dto);
			return ResponseEntity.ok("Senha alterado com sucesso");
			
		} 	catch (RuntimeException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listar() {
		return ResponseEntity.ok(UsuarioService.listarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscar(@PathVariable Long id) {
		return UsuarioService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
		return ResponseEntity.ok(UsuarioService.atualizar(id, usuario));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		UsuarioService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
