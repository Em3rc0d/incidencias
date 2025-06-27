package com.incidencias.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class IncidenciaDTO {
    private Long id;
    private String usuarioNombre; 

    private Long vehiculoId;
    private Long usuarioId;
    private Long tipoIncidenciaId;
    private String descripcion;
    private String prioridad;
    private String estado;
    private LocalDateTime fechaReporte;
    private Double latitud;
    private Double longitud;
}
