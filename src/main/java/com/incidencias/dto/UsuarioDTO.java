package com.incidencias.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long empresaId;
    private String nombre;
    private String correo;
    private String contrasena;
    private String rol;
}
