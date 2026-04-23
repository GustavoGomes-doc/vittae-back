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
	
	private int tempoConsulta;
	private LocalDate dataNascimento;
	private String cep;
	private BigDecimal valorConsulta;
	private String telefone;
	
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@ManyToMany
	@JoinTable(name = "especialidade_medico", joinColumns = @JoinColumn(name = "id_medico"), inverseJoinColumns = @JoinColumn(name = "id_especialidade"))
	private List<Especialidade> especialidades;

	@OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Disponibilidade> disponibilidades;

	@OneToMany(mappedBy = "medico")
	private List<Consulta> consultas;

	public Medico() {
	}

	public Medico(byte[] foto, LocalDate dataNascimento, String crm, String cep,BigDecimal valorConsulta, String ufCrm, int tempoConsulta, String rqe, String biografia, List especialidades, String telefone) {
		this.foto = foto;
		this.dataNascimento = dataNascimento;
		this.crm = crm;
		this.cep = cep;
		this.valorConsulta = valorConsulta;
		this.ufCrm = ufCrm;
		this.rqe = rqe;
		this.biografia = biografia;
		this.tempoConsulta = tempoConsulta;
		this.especialidades = especialidades;
		this.telefone = telefone;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public String getUfCrm() {
		return ufCrm;
	}

	public void setUfCrm(String ufCrm) {
		this.ufCrm = ufCrm;
	}

	public String getRqe() {
		return rqe;
	}

	public void setRqe(String rqe) {
		this.rqe = rqe;
	}

	public Integer getTempoConsulta() {
		return tempoConsulta;
	}

	public void setTempoConsulta(Integer tempoConsultaMinutos) {
		this.tempoConsulta = tempoConsultaMinutos;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}



	public BigDecimal getValorConsulta() {
		return valorConsulta;
	}

	public void setValorConsulta(BigDecimal valorConsulta) {
		this.valorConsulta = valorConsulta;
	}

	public void setTempoConsulta(int tempoConsulta) {
		this.tempoConsulta = tempoConsulta;
	}

	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}

	public List<Disponibilidade> getDisponibilidades() {
		return disponibilidades;
	}

	public void setDisponibilidades(List<Disponibilidade> disponibilidades) {
		this.disponibilidades = disponibilidades;
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}
}