package com.vittae.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.vittae.model.enums.Status;

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

	private LocalDate dataAgendado;
	private LocalDate dataConsulta;
	private LocalTime hora;
	private BigDecimal valorConsulta;

}