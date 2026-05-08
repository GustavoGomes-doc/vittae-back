package com.vittae.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vittae.model.Medico;
import com.vittae.repository.CadastrarMedicoRepository;

@RestController
@RequestMapping("/api/disponibilidade")
@CrossOrigin(origins = "*")
public class DisponibilidadeController {

    @Autowired
    private CadastrarMedicoRepository medicoRepository;

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
}