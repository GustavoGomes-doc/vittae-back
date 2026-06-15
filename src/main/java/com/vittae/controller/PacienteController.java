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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vittae.dto.PacienteAdminDTO;
import com.vittae.model.Paciente;
import com.vittae.repository.PacienteRepository;
import com.vittae.service.PacienteService;

@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin(origins = "*")
public class PacienteController {

	@Autowired
	private PacienteService PacienteService;
	
	@GetMapping("/admin")
	public ResponseEntity<List<PacienteAdminDTO>> listarAdmin(
	        @RequestHeader("Authorization") String auth) {
	    List<Paciente> pacientes = PacienteService.listarTodos();
	    List<PacienteAdminDTO> dtos = pacientes.stream()
	        .map(PacienteAdminDTO::new)
	        .collect(java.util.stream.Collectors.toList());
	    return ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Paciente> buscar(@PathVariable Long id) {
		return PacienteService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Paciente> cadastrar(@RequestBody Paciente paciente) {
		Paciente pacienteSalvo = PacienteService.salvar(paciente);
		return ResponseEntity.ok(pacienteSalvo);
	}
	
	@GetMapping
	public ResponseEntity<List<Paciente>> listar() {
		return ResponseEntity.ok(PacienteService.listarTodos());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Paciente> atualizar(@PathVariable Long id, @RequestBody Paciente paciente) {
		return ResponseEntity.ok(PacienteService.atualizar(id, paciente));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		PacienteService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
