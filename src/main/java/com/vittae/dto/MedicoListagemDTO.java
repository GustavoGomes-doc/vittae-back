package com.vittae.dto;

import java.util.Base64;
import java.util.List;

import com.vittae.model.Medico;

public class MedicoListagemDTO {
	private Long id;
	private String nome;
	private Integer tempoConsultaMinutos;
	private Double valorConsulta;
	private String foto;
	private String telefone;
	private String crm;
	private List<String> especialidades;

	public MedicoListagemDTO(Medico m) {
		this.id = m.getId();
		this.nome = m.getNome();
		this.tempoConsultaMinutos = m.getTempoConsultaMinutos();
		this.valorConsulta = m.getValorConsulta() != null ? m.getValorConsulta().doubleValue() : null;
		this.foto = m.getFoto() != null ? Base64.getEncoder().encodeToString(m.getFoto()) : null;
		this.telefone = m.getTelefone();
		this.crm = m.getCrm();
		this.especialidades = m.getEspecialidades() != null
			    ? m.getEspecialidades().stream()
			        .map(e -> e.getNome())
			        .collect(java.util.stream.Collectors.toList())
			    : new java.util.ArrayList<>();
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

	public Double getValorConsulta() {
		return valorConsulta;
	}

	public void setValorConsulta(Double v) {
		this.valorConsulta = v;
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


	public List<String> getEspecialidades() {
		return especialidades;
	}


	public void setEspecialidades(List<String> especialidades) {
		this.especialidades = especialidades;
	}
	
	
	
}