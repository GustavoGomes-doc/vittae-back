package com.vittae.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "paciente")
@PrimaryKeyJoinColumn(name = "id_usuario")
public class Paciente extends Usuario {

	private LocalDate dataNascimento;
	private String telefone;
	private String endereco;
	private String cidade;
	
	@ManyToOne
	@JoinColumn(name = "id_responsavel")
	private Paciente responsavel;
}