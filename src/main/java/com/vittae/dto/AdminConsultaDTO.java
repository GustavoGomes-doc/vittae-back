package com.vittae.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import com.vittae.model.Consulta;
import com.vittae.model.enums.Status;
import lombok.Data;

@Data
public class AdminConsultaDTO {
    private Long id;
    private String nomePaciente;
    private String nomeMedico;
    private String especialidade;
    private LocalDate dataConsulta;
    private LocalTime hora;
    private Status status;
    private BigDecimal valorConsulta;

    public AdminConsultaDTO(Consulta c) {
        this.id = c.getId();
        this.nomePaciente = c.getPaciente() != null ? c.getPaciente().getNome() : "—";
        this.nomeMedico = c.getMedico() != null ? c.getMedico().getNome() : "—";
        this.especialidade = c.getEspecialidade() != null ? c.getEspecialidade().getNome() : "—";
        this.dataConsulta = c.getDataConsulta();
        this.hora = c.getHora();
        this.status = c.getStatus();
        this.valorConsulta = c.getValorConsulta();
    }
}