package com.incidencias.dto;

public class ChoferIncidenciasDTO {
    private String nombre;
    private Long cantidad;

    public ChoferIncidenciasDTO(String nombre, Long cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public Long getCantidad() {
        return cantidad;
    }
}
