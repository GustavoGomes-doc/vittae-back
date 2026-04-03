package com.vittae.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity

public class CadastrarMedico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String crm;
	private String email;
	private String cpf;
	private String cep;
	private LocalDate dataNascimento;
	private String senha;
	private BigDecimal valorConsulta;
	
	@ManyToMany
	@JoinTable(
	        name = "medico_tem_especialidade", //
	        joinColumns = @JoinColumn(name = "medico_id"),
	        inverseJoinColumns = @JoinColumn(name = "especialidade_id")
	    )
	private List<Especialidade> especialidades;

	public CadastrarMedico () {}
	
	public CadastrarMedico(Long id, String nome, String crm, String email, String cpf, String cep, LocalDate dataNascimento, BigDecimal valorConsulta, String senha) {
		this.id = id;
		this.nome = nome;
		this.crm = crm;
		this.cpf = cpf;
		this.cep = cep;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.setValorConsulta(valorConsulta);
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

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public BigDecimal getValorConsulta() {
		return valorConsulta;
	}

	public void setValorConsulta(BigDecimal valorConsulta) {
		this.valorConsulta = valorConsulta;
	}

}