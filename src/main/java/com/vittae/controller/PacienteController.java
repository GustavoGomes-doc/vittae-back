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

import com.vittae.model.Paciente;
import com.vittae.repository.PacienteRepository;
import com.vittae.service.PacienteService;

@RestController
@RequestMapping("/api/pacientes")

public class PacienteController {

	@Autowired
	private PacienteService cadastrarPacienteService;
	private PacienteRepository cadastrarPacienteRepository;

	@PostMapping
	public ResponseEntity<Paciente> cadastrar(@RequestBody Paciente paciente) {
		Paciente pacienteSalvo = cadastrarPacienteService.salvar(paciente);
		return ResponseEntity.ok(pacienteSalvo);
	}
	
	@GetMapping
	public ResponseEntity<List<Paciente>> listar() {
		return ResponseEntity.ok(cadastrarPacienteService.listarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Paciente> buscar(@PathVariable Long id) {
		return cadastrarPacienteService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Paciente> atualizar(@PathVariable Long id, @RequestBody Paciente paciente) {
		return ResponseEntity.ok(cadastrarPacienteService.atualizar(id, paciente));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		cadastrarPacienteService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
