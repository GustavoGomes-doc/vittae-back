package com.vittae.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.vittae.model.Medico;
import com.vittae.model.enums.Status;

import lombok.Data;

@Data
public class VisualizarConsultaDTO {

    private Long id;
    private BigDecimal valorConsulta; 
    private LocalDate dataConsulta;
    private LocalTime hora;
    private Medico medico;
    private String local;
    private Status status; 
}