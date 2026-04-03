package com.vittae.controller;

import java.util.List;

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

import com.vittae.model.Exame;
import com.vittae.service.ExameService;

@RestController
@RequestMapping("api/exames")
public class ExameController {

	@Autowired
	private ExameService exameService;

	@PostMapping
	public ResponseEntity<Exame> cadastrar(@RequestBody Exame exame) {
		Exame exameSalvo = exameService.salvar(exame);
		return ResponseEntity.ok(exameSalvo);
	}
	
	@GetMapping
	public ResponseEntity<List<Exame>> listar() {
		return ResponseEntity.ok(exameService.listarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Exame> buscar(@PathVariable Long id) {
		return exameService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Exame> atualizar(@PathVariable Long id, @RequestBody Exame exame) {
		return ResponseEntity.ok(exameService.atualizar(id, exame));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		exameService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}