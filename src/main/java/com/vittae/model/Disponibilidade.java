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
import lombok.Data;

@Data
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
	
}