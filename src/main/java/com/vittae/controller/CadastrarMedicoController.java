package com.vittae.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vittae.model.Medico;
import com.vittae.service.CadastrarMedicoService;

@RestController
@RequestMapping("/medicos")
public class CadastrarMedicoController {

	// Aqui usamos o nome exato da dependência que você colocou no diagrama
	@Autowired
	private CadastrarMedicoService cadastrarMedicoService;

	@PostMapping
	public ResponseEntity<Medico> cadastrar(@RequestBody Medico medico) {
		Medico medicoSalvo = cadastrarMedicoService.salvar(medico);
		return ResponseEntity.ok(medicoSalvo);
	}

	@GetMapping
	public ResponseEntity<List<Medico>> listar() {
		return ResponseEntity.ok(service.listarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Medico> buscar(@PathVariable Long id) {
		return service.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Medico> atualizar(@PathVariable Long id, @RequestBody Medico medico) {
		return ResponseEntity.ok(service.atualizar(id, medico));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
}