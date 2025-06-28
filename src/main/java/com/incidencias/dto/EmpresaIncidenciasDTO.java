package com.incidencias.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

public class EmpresaIncidenciasDTO {
    private String nombreEmpresa;
    private Long total;
    private Long abiertas;
    private Long pendientes;
    private Long cerradas;

    public EmpresaIncidenciasDTO(String nombreEmpresa, Long total, Long abiertas, Long pendientes, Long cerradas) {
        this.nombreEmpresa = nombreEmpresa;
        this.total = total;
        this.abiertas = abiertas;
        this.pendientes = pendientes;
        this.cerradas = cerradas;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public Long getTotal() {
        return total;
    }

    public Long getAbiertas() {
        return abiertas;
    }

    public Long getPendientes() {
        return pendientes;
    }
    
    public Long getCerradas() {
        return cerradas;
    }
}
