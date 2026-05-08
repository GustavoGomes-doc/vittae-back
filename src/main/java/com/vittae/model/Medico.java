package com.vittae.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "medico")
@PrimaryKeyJoinColumn(name = "id_usuario")
public class Medico extends Usuario {

	@Lob
	private byte[] foto;
	
	@Column(columnDefinition = "TEXT")
	private String biografia;
	
	@Column(nullable = false)
    private String crm;
	
	@Column(length = 2)
    private String ufCrm;

	private String rqe;
	
	private Integer tempoConsulta;
	private LocalDate dataNascimento;
	private String cep;
	private BigDecimal valorConsulta;
	private String telefone;
	
	@ManyToMany
	@JoinTable(name = "especialidade_medico", joinColumns = @JoinColumn(name = "id_medico"), inverseJoinColumns = @JoinColumn(name = "id_especialidade"))
	private List<Especialidade> especialidades;

	@OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Disponibilidade> disponibilidades;
	
}