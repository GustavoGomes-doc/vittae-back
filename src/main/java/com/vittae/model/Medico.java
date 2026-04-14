package com.vittae.model;

import java.time.LocalDate;
import java.util.List;

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

	private LocalDate dataNascimento;
	private String crm;
	private String cep;
	private int valorConsulta;
	private String telefone;

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@ManyToMany
	@JoinTable(name = "medico_especialidade", joinColumns = @JoinColumn(name = "id_medico"), inverseJoinColumns = @JoinColumn(name = "id_especialidade"))
	private List<Especialidade> especialidades;

	@OneToMany(mappedBy = "medico")
	private List<Disponibilidade> disponibilidades;

	@OneToMany(mappedBy = "medico")
	private List<Consulta> consultas;

	public Medico() {
	}

	public Medico(byte[] foto, LocalDate dataNascimento, String crm, String cep, int valorConsulta) {
		this.foto = foto;
		this.dataNascimento = dataNascimento;
		this.crm = crm;
		this.cep = cep;
		this.valorConsulta = valorConsulta;
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

	public int getValorConsulta() {
		return valorConsulta;
	}

	public void setValorConsulta(int valorConsulta) {
		this.valorConsulta = valorConsulta;
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