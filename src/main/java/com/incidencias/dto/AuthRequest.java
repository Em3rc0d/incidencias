package com.incidencias.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    private String correo;
    private String contrasena;
}
