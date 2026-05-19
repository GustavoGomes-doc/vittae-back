package com.vittae.dto;

import java.util.List;

public class HorariosLivresDTO {
	private List<String> diasDisponiveis;
	private List<String> horariosLivres; 

	public HorariosLivresDTO(List<String> diasDisponiveis, List<String> horariosLivres) {
		this.diasDisponiveis = diasDisponiveis;
		this.horariosLivres = horariosLivres;
	}

	public List<String> getDiasDisponiveis() {
		return diasDisponiveis;
	}

	public List<String> getHorariosLivres() {
		return horariosLivres;
	}
}