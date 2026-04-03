package com.vittae.controller;

import java.util.List;

import org.jspecify.annotations.Nullable;
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

import com.vittae.model.Consulta;
import com.vittae.repository.ConsultaRepository;
import com.vittae.service.ConsultaService;

@RestController
@RequestMapping("api/consultas")

public class ConsultaController {

	@Autowired
	private ConsultaService consultaService;
	private ConsultaRepository consultaRepository;

	@PostMapping
	public ResponseEntity<Consulta> cadastrar(@RequestBody Consulta consulta) {
		Consulta consultaSalva = consultaService.salvar(consulta);
		return ResponseEntity.ok(consultaSalva);
	}
	
	@GetMapping
	public ResponseEntity<List<Consulta>> listar() {
		return ResponseEntity.ok(consultaService.listarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Consulta> buscar(@PathVariable Long id) {
		return consultaService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody Consulta consulta) {
		return ResponseEntity.ok(consultaService.atualizar(id, consulta));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		consultaService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}