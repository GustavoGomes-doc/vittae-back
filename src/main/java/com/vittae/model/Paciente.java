package com.vittae.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "paciente")
@PrimaryKeyJoinColumn(name = "id_usuario")
public class Paciente extends Usuario {

	private LocalDate dataNascimento;
	private String telefone;

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@OneToOne
	@JoinColumn(name = "id_responsavel")
	private Paciente responsavel;

	@OneToMany(mappedBy = "paciente")
	private List<Consulta> consultas;

	public Paciente() {
	}

	public Paciente(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public LocalDate getdataNascimento() {
		return dataNascimento;
	}

	public void setDataNasc(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Paciente getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Paciente responsavel) {
		this.responsavel = responsavel;
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}
}