package com.vittae.model;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity

public class Consulta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date dataAgendado;
	private Date dataConsulta;
	private LocalDate hora;
	private int valorconsulta;
	
	@Enumerated(EnumType.STRING)
    private Status status;
	
    @OneToMany(mappedBy = "consulta")
    private java.util.List<Exame> exames;
    
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private CadastrarMedico medico;
	

	public java.util.List<Exame> getExames() {
		return exames;
	}

	public void setExames(java.util.List<Exame> exames) {
		this.exames = exames;
	}

	public Consulta () {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getDataAgendado() {
		return dataAgendado;
	}

	public void setDataAgendado(Date dataAgendado) {
		this.dataAgendado = dataAgendado;
	}

	public Date getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public LocalDate getHora() {
		return hora;
	}

	public void setHora(LocalDate hora) {
		this.hora = hora;
	}

	public int getValorconsulta() {
		return valorconsulta;
	}

	public void setValorconsulta(int valorconsulta) {
		this.valorconsulta = valorconsulta;
	}
	
}