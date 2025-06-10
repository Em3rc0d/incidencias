package com.incidencias.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class VehiculoDTO {
    private Long empresaId;
    private String placa;
    private String marca;
    private String modelo;
    private Integer anio;
    private String tipo;
    private String estado;
    // Getters y setters
}