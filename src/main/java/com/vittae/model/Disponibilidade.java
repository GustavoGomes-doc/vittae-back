package com.vittae.model;

import java.time.LocalTime;

import com.vittae.model.enums.DiaSemana;

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
@Table(name = "disponibilidade")
public class Disponibilidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalTime horaInicio;
	private LocalTime horaFim;

	@Enumerated(EnumType.STRING)
	private DiaSemana diaSemana;

	@ManyToOne
	@JoinColumn(name = "id_medico")
	private Medico medico;

	public Disponibilidade() {
	}

	public Disponibilidade(LocalTime horaInicio, LocalTime horaFim, DiaSemana diaSemana, Medico medico) {
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.diaSemana = diaSemana;
		this.medico = medico;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(LocalTime horaFim) {
		this.horaFim = horaFim;
	}

	public DiaSemana getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(DiaSemana diaSemana) {
		this.diaSemana = diaSemana;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}


	
	
}