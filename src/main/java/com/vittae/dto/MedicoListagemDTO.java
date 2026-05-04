package com.vittae.dto;

import java.util.List;

import com.vittae.model.Medico;

public class MedicoListagemDTO {
    private Long id;
    private String nome;
    private String crm;
    private String telefone;
    private List<String> especialidades;

    public MedicoListagemDTO(Medico m) {
        this.id = m.getId();
        this.nome = m.getNome();
        this.crm = m.getCrm();
        this.telefone = m.getTelefone();
        this.especialidades = m.getEspecialidades()
            .stream()
            .map(e -> e.getNome()) // ajusta pro campo correto da entidade Especialidade
            .collect(java.util.stream.Collectors.toList());
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<String> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<String> especialidades) {
		this.especialidades = especialidades;
	}

    
}