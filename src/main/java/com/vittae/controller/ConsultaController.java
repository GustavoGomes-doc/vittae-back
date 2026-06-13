package com.vittae.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.vittae.dto.AdminConsultaDTO;
import com.vittae.dto.AgendamentoDTO;
import com.vittae.dto.PacienteListagemDTO;
import com.vittae.model.Consulta;
import com.vittae.repository.ConsultaRepository;
import com.vittae.service.ConsultaService;

@RestController
@RequestMapping("api/agendamentos")
@CrossOrigin(origins = "*")
public class ConsultaController {
	
	@Autowired
	private ConsultaRepository consultaRepository;

    @Autowired
    private ConsultaService consultaService;
    
    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<PacienteListagemDTO>> listarPorMedico(@PathVariable Long medicoId) {
        List<Consulta> consultas = consultaService.listarPorMedico(medicoId);
        List<PacienteListagemDTO> dtos = consultas.stream()
            .map(PacienteListagemDTO::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
    
    @GetMapping("/todos")
    public ResponseEntity<List<AdminConsultaDTO>> listarTodas() {
    	List<Consulta> consultas = consultaService.listarTodas();
    	List<AdminConsultaDTO> dtos = consultas.stream()
    			.map(AdminConsultaDTO::new)
    			.collect(Collectors.toList());
    	return ResponseEntity.ok(dtos);
    	
    }
    
    @GetMapping("/pacientes/{pacienteId}")
    public ResponseEntity<List<AdminConsultaDTO>> listarPorPaciente(@PathVariable Long pacienteId) {
    	List<Consulta> consultas = consultaService.listarPorPaciente(pacienteId);
    	List<AdminConsultaDTO> dtos = consultas.stream()
    			.map(AdminConsultaDTO::new)
    			.collect(Collectors.toList());
    	return ResponseEntity.ok(dtos);
    	
    }

	@PostMapping
	public ResponseEntity<?> salvarAgendamento(@RequestBody AgendamentoDTO dto) {
	    try {
	        consultaService.salvarAgendamento(dto); 
	        return ResponseEntity.ok().body("{\"mensagem\": \"Agendamento realizado!\"}");
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body("Erro ao agendar: " + e.getMessage());
	    }
	}
	
	@GetMapping
	public ResponseEntity<List<AdminConsultaDTO>> listar() {
	    List<Consulta> consultas = consultaService.listarTodas();
	    List<AdminConsultaDTO> dtos = consultas.stream()
	        .map(AdminConsultaDTO::new)
	        .collect(Collectors.toList());
	    return ResponseEntity.ok(dtos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Consulta> buscar(@PathVariable Long id) {
		return consultaService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Consulta consulta) {
	    try {
	        Consulta consultaAtualizada = consultaService.atualizar(id, consulta);
	        return ResponseEntity.ok(consultaAtualizada);
	    } catch (RuntimeException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		consultaService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}