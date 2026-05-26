package com.vittae.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "consulta")
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_medico")
	private Medico medico;

	@ManyToOne
	@JoinColumn(name = "id_paciente")
	private Paciente paciente;

	@Enumerated(EnumType.STRING)
	private Status status;

	private LocalDate dataConsulta;
	private LocalTime hora;
	
	@ManyToOne
    @JoinColumn(name = "id_especialidade")
    private Especialidade especialidade;
	
	private BigDecimal valorConsulta;
	
	private String observacoes;
	
	@Column(name = "resp_nome")
    private String respNome;

    @Column(name = "resp_cpf")
    private String respCpf;

    @Column(name = "resp_parentesco")
    private String respParentesco;
    
    private LocalDate respDataNascimento;

	public Consulta() {
	}

	public Consulta(Medico medico, Paciente paciente, Status status, LocalDate dataAgendado, LocalDate dataConsulta,
			BigDecimal valorConsulta, LocalTime hora, Especialidade especialidade, String respNome, String respCpf, String respParentesco, LocalDate respDataNascimento) {
		this.medico = medico;
		this.paciente = paciente;
		this.status = status;
		this.dataConsulta = dataConsulta;
		this.hora = hora;
		this.valorConsulta = valorConsulta;
		this.especialidade = especialidade;
		this.respCpf = respCpf;
		this.respNome = respNome;
		this.respParentesco = respParentesco;
		this.respDataNascimento = respDataNascimento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}


	public LocalDate getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(LocalDate dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public BigDecimal getValorConsulta() {
		return valorConsulta;
	}

	public void setValorConsulta(BigDecimal valorConsulta) {
		this.valorConsulta = valorConsulta;
	}

	public String getRespNome() {
		return respNome;
	}

	public void setRespNome(String respNome) {
		this.respNome = respNome;
	}

	public String getRespCpf() {
		return respCpf;
	}

	public void setRespCpf(String respCpf) {
		this.respCpf = respCpf;
	}

	public String getRespParentesco() {
		return respParentesco;
	}

	public void setRespParentesco(String respParentesco) {
		this.respParentesco = respParentesco;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public LocalDate getRespDataNascimento() {
		return respDataNascimento;
	}

	public void setRespDataNascimento(LocalDate respDataNascimento) {
		this.respDataNascimento = respDataNascimento;
	}
	
	
	
}