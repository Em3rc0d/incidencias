package com.incidencias.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReporteAseguradoraDTO {

    private Long incidencia;
    private Long aseguradora;
    private LocalDateTime fechaEnvio;
    private String estadoEnvio;

    private String asunto;
    private String cuerpo;
    private String observaciones;
}
