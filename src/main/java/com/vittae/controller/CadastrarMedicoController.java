package com.vittae.controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import com.vittae.dto.CadastrarMedicoDTO;
import com.vittae.model.Medico;
import com.vittae.service.CadastrarMedicoService;
 
@RestController
@RequestMapping("/api/medicos")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class CadastrarMedicoController {
 
    @Autowired
    private CadastrarMedicoService cadastrarMedicoService;
 
    // ✅ Recebe DTO em vez de Medico diretamente
    @PostMapping
    public ResponseEntity<Medico> cadastrar(@RequestBody CadastrarMedicoDTO dto) {
        Medico medicoSalvo = cadastrarMedicoService.salvarDTO(dto);
        return ResponseEntity.ok(medicoSalvo);
    }
 
    @GetMapping
    public ResponseEntity<List<Medico>> listar() {
        return ResponseEntity.ok(cadastrarMedicoService.listarTodos());
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Medico> buscar(@PathVariable Long id) {
        return cadastrarMedicoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<Medico> atualizar(@PathVariable Long id, @RequestBody Medico medico) {
        return ResponseEntity.ok(cadastrarMedicoService.atualizar(id, medico));
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        cadastrarMedicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
