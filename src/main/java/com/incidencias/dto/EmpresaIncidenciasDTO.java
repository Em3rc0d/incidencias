package com.incidencias.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

public class EmpresaIncidenciasDTO {
    private String nombreEmpresa;
    private Long cantidadIncidencias;

    public EmpresaIncidenciasDTO(String nombreEmpresa, Long cantidadIncidencias) {
        this.nombreEmpresa = nombreEmpresa;
        this.cantidadIncidencias = cantidadIncidencias;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public Long getCantidadIncidencias() {
        return cantidadIncidencias;
    }
}
