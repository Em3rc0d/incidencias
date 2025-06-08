// EmpresaDTO.java
package com.incidencias.dto;

import lombok.Data;

@Data
public class EmpresaDTO {
    private Long id;
    private Long aseguradoraId;
    private String nombre;
    private String ruc;
}
