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

import com.vittae.dto.AgendamentoDTO;
import com.vittae.model.Consulta;
import com.vittae.service.ConsultaService;

@RestController
@RequestMapping("api/agendamentos")
@CrossOrigin(origins = "*")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;
    
    @GetMapping
    public ResponseEntity<List<Consulta>> listarTodos() {
        return ResponseEntity.ok(consultaService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<?> salvarAgendamento(@RequestBody AgendamentoDTO dto) {
        try {
            consultaService.salvarAgendamento(dto);
            return ResponseEntity.ok().body("{\"mensagem\": \"Agendamento rea	lizado!\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao agendar: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscar(@PathVariable Long id) {
        return consultaService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> atualizar(@PathVariable Long id, @RequestBody Consulta consulta) {
        return ResponseEntity.ok(consultaService.atualizar(id, consulta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        consultaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}