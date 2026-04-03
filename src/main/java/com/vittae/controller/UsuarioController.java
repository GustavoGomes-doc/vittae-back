package com.vittae.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vittae.model.CadastrarUsuario;
import com.vittae.repository.CadastrarUsuarioRepository;
import com.vittae.service.CadastrarUsuarioService;

@RestController
@RequestMapping("/vittae")

public class CadastrarUsuarioController {

	@Autowired
	private CadastrarUsuarioService cadastrarUsuarioService;
	private CadastrarUsuarioRepository cadastrarUsuarioRepository;

	@PostMapping
	public ResponseEntity<CadastrarUsuario> cadastrar(@RequestBody CadastrarUsuario usuario) {
		CadastrarUsuario usuarioSalvo = cadastrarUsuarioService.salvar(usuario);
		return ResponseEntity.ok(usuarioSalvo);
	}
	
	@GetMapping
	public ResponseEntity<List<CadastrarUsuario>> listar() {
		return ResponseEntity.ok(cadastrarUsuarioService.listarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CadastrarUsuario> buscar(@PathVariable Long id) {
		return cadastrarUsuarioService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<CadastrarUsuario> atualizar(@PathVariable Long id, @RequestBody CadastrarUsuario usuario) {
		return ResponseEntity.ok(cadastrarUsuarioService.atualizar(id, usuario));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		cadastrarUsuarioService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
