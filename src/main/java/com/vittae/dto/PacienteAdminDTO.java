package com.vittae.dto;

import com.vittae.model.Paciente;
import java.time.LocalDate;

public class PacienteAdminDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;
    private String genero;
    private long qtdConsultas;

    public PacienteAdminDTO(Paciente p) {
        this.id = p.getId();
        this.nome = p.getNome();
        this.cpf = p.getCpf();
        this.email = p.getEmail();
        this.telefone = p.getTelefone();
        this.dataNascimento = p.getDataNascimento();
        this.genero = p.getGenero();
        this.qtdConsultas = p.getConsultas() != null ? p.getConsultas().size() : 0;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public String getGenero() { return genero; }
    public long getQtdConsultas() { return qtdConsultas; }
}