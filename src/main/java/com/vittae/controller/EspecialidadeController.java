package com.vittae.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vittae.repository.EspecialidadeRepository;

@RestController
@RequestMapping("/api/especialidades")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:8081"})
public class EspecialidadeController {

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @GetMapping
    public List<EspecialidadeDTO> listar() {
        return especialidadeRepository.findAll()
            .stream()
            .map(e -> new EspecialidadeDTO(e.getNome(), e.getDescricao()))
            .collect(Collectors.toList());
    }
    
    

    public static class EspecialidadeDTO {
        public String nome;
        public String descricao;
        public EspecialidadeDTO(String nome, String descricao) {
            this.nome = nome;
            this.descricao = descricao;
        }
    }
}