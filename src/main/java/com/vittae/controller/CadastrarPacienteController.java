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

import com.vittae.model.CadastrarPaciente;
import com.vittae.repository.CadastrarPacienteRepository;
import com.vittae.service.CadastrarPacienteService;

@RestController
@RequestMapping("/pacientes")

public class CadastrarPacienteController {

	@Autowired
	private CadastrarPacienteService cadastrarPacienteService;
	private CadastrarPacienteRepository cadastrarPacienteRepository;

	@PostMapping
	public ResponseEntity<CadastrarPaciente> cadastrar(@RequestBody CadastrarPaciente paciente) {
		CadastrarPaciente pacienteSalvo = cadastrarPacienteService.salvar(paciente);
		return ResponseEntity.ok(pacienteSalvo);
	}
	
	@GetMapping
	public ResponseEntity<List<CadastrarPaciente>> listar() {
		return ResponseEntity.ok(cadastrarPacienteService.listarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CadastrarPaciente> buscar(@PathVariable Long id) {
		return cadastrarPacienteService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<CadastrarPaciente> atualizar(@PathVariable Long id, @RequestBody CadastrarPaciente paciente) {
		return ResponseEntity.ok(cadastrarPacienteService.atualizar(id, paciente));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		cadastrarPacienteService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
