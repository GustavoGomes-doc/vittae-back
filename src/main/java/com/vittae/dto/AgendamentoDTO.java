package com.vittae.dto;

import java.time.LocalTime;
import java.util.Date;

public class AgendamentoDTO {

	private String nomePaciente;
	private String cpfPaciente;
	private String dataNascimento;
	private String telefone;
	private String sexoBiologico;
	private String observacoes;
	private LocalTime hora;

	private Long medicoId;
	private Long pacienteId;

	public AgendamentoDTO() {
	}

	// Getters e Setters
	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getCpfPaciente() {
		return cpfPaciente;
	}

	public void setCpfPaciente(String cpfPaciente) {
		this.cpfPaciente = cpfPaciente;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSexoBiologico() {
		return sexoBiologico;
	}

	public void setSexoBiologico(String sexoBiologico) {
		this.sexoBiologico = sexoBiologico;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Long getMedicoId() {
		return medicoId;
	}

	public void setMedicoId(Long medicoId) {
		this.medicoId = medicoId;
	}

	public Long getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(Long pacienteId) {
		this.pacienteId = pacienteId;
	}

	public Date getDataAgendado() {
		// TODO Auto-generated method stub
		return null;
	}

	public Date getDataConsulta() {
		// TODO Auto-generated method stub
		return null;
	}

	public LocalTime getHora() {
		// TODO Auto-generated method stub
		return null;
	}
}