package com.incidencias.dto;

import java.time.LocalDateTime;

public class IncidenciaAfectadosDTO {
    private Long id;
    private String descripcion;
    private String usuarioNombre;
    private LocalDateTime fechaReporte;
    private Long cantidadAfectados;

    public IncidenciaAfectadosDTO(Long id, String descripcion, String usuarioNombre, LocalDateTime fechaReporte, Long cantidadAfectados) {
        this.id = id;
        this.descripcion = descripcion;
        this.usuarioNombre = usuarioNombre;
        this.fechaReporte = fechaReporte;
        this.cantidadAfectados = cantidadAfectados;
    }

    public Long getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public String getUsuarioNombre() { return usuarioNombre; }
    public LocalDateTime getFechaReporte() { return fechaReporte; }
    public Long getCantidadAfectados() { return cantidadAfectados; }
}
