package com.incidencias.dto;

import lombok.Data;

@Data
public class AfectadoDTO {
    private Long incidenciaId;
    private String nombre;
    private String documentoIdentidad;
    private String tipoTercero;
    private String contacto;
    private String descripcionDanio;
}
