package com.vittae.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class CadastrarMedicoDTO {

	private String nome;
	private String cpf;
	private String email;
	private String senha;
	private LocalDate dataNascimento;
	private String crm;
	private String ufCrm;
	private String rqe;
	private String cep;
	private BigDecimal valorConsulta; // Alterado para BigDecimal
	private Integer tempoConsulta;    // Renomeado para tempoConsulta
	private String biografia;
	private List<String> especialidades;
	private List<DisponibilidadeDTO> disponibilidades;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	public Integer getTempoConsulta() {
		return tempoConsulta;
	}

	public void setTempoConsulta(Integer tempoConsulta) {
		this.tempoConsulta = tempoConsulta;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public List<String> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<String> especialidades) {
		this.especialidades = especialidades;
	}

	public List<DisponibilidadeDTO> getDisponibilidades() {
		return disponibilidades;
	}

	public void setDisponibilidades(List<DisponibilidadeDTO> disponibilidades) {
		this.disponibilidades = disponibilidades;
	}

	// ── DTO interno ───────────────────────────────────────────

	public static class DisponibilidadeDTO {

		private String diaSemana;
		private String horaInicio;
		private String horaFim;

		public String getDiaSemana() {
			return diaSemana;
		}

		public void setDiaSemana(String diaSemana) {
			this.diaSemana = diaSemana;
		}

		public String getHoraInicio() {
			return horaInicio;
		}

		public void setHoraInicio(String horaInicio) {
			this.horaInicio = horaInicio;
		}

		public String getHoraFim() {
			return horaFim;
		}

		public void setHoraFim(String horaFim) {
			this.horaFim = horaFim;
		}
	}
}