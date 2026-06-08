package com.vittae.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vittae.model.Disponibilidade;
import com.vittae.model.Medico;
import com.vittae.model.enums.DiaSemana;
import com.vittae.repository.CadastrarMedicoRepository;
import com.vittae.repository.DisponibilidadeRepository;

@RestController
@RequestMapping("/api/disponibilidade")
@CrossOrigin(origins = "*")
public class DisponibilidadeController {

    @Autowired
    private CadastrarMedicoRepository medicoRepository;
    
    @Autowired
    private DisponibilidadeRepository disponibilidadeRepository;

    @GetMapping("/{medicoId}")
    public ResponseEntity<?> buscarDisponibilidade(@PathVariable Long medicoId) {
        Medico medico = medicoRepository.findById(medicoId)
            .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        List<Map<String, String>> result = medico.getDisponibilidades()
            .stream()
            .map(d -> {
                Map<String, String> map = new java.util.HashMap<>();
                map.put("diaSemana", d.getDiaSemana().name());
                map.put("horaInicio", d.getHoraInicio().toString());
                map.put("horaFim", d.getHoraFim().toString());
                return map;
            })
            .collect(java.util.stream.Collectors.toList());

        return ResponseEntity.ok(result);
    }
    
    @PostMapping("/{medicoId}")
    public ResponseEntity<?> adicionarDisponibilidade(
            @PathVariable Long medicoId,
            @RequestBody Map<String, String> body) {
        try {
            Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

            Disponibilidade d = new Disponibilidade();
            d.setDiaSemana(DiaSemana.valueOf(body.get("diaSemana")));
            d.setHoraInicio(body.get("horaInicio"));
            d.setHoraFim(body.get("horaFim"));
            d.setMedico(medico);

            disponibilidadeRepository.save(d);
            return ResponseEntity.ok(Map.of("mensagem", "Disponibilidade salva!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerDisponibilidade(@PathVariable Long id) {
    	try {	
    		disponibilidadeRepository.deleteById(id);
    		return ResponseEntity.noContent().build()	;
    		
    	} catch (Exception e) {
    		return ResponseEntity.badRequest().body(e.getMessage());
    	}
    }
    
    
    
}