package com.vittae.dto;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import com.vittae.controller.EspecialidadeController.EspecialidadeDTO;
import com.vittae.model.Medico;

public class MedicoListagemDTO {
	private Long id;
	private String nome;
	private Integer tempoConsultaMinutos;
	private String ufCrm;
	private Double valorConsulta;
	private String email;
	private String foto;
	private String telefone;
	private String crm;
	private List<EspecialidadeDTO> especialidades;

	public MedicoListagemDTO(Medico m) {
		this.id = m.getId();
		this.nome = m.getNome();
		this.tempoConsultaMinutos = m.getTempoConsultaMinutos();
		this.valorConsulta = m.getValorConsulta() != null ? m.getValorConsulta().doubleValue() : null;
		this.email = m.getEmail();
		this.ufCrm = m.getUfCrm();
		this.foto = m.getFoto() != null ? Base64.getEncoder().encodeToString(m.getFoto()) : null;
		this.telefone = m.getTelefone();
		this.crm = m.getCrm();
		this.especialidades = m.getEspecialidades() != null ? m.getEspecialidades().stream()
				.map(e -> new EspecialidadeDTO(e.getId(), e.getNome(), e.getDescricao())).collect(Collectors.toList())
				: new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getTempoConsultaMinutos() {
		return tempoConsultaMinutos;
	}

	public void setTempoConsultaMinutos(Integer t) {
		this.tempoConsultaMinutos = t;
	}

	public String getUfCrm() {
		return ufCrm;
	}

	public void setUfCrm(String v) {
		this.ufCrm = v;
	}

	public Double getValorConsulta() {
		return valorConsulta;
	}

	public void setValorConsulta(Double v) {
		this.valorConsulta = v;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String v) {
		this.email = v;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public List<EspecialidadeDTO> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<EspecialidadeDTO> v) {
		this.especialidades = v;
	}

}