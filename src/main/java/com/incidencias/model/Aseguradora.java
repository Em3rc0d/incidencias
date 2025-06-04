// Aseguradora.java
package com.incidencias.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "aseguradora")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aseguradora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aseguradora_id")
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "correo_contacto")
    @JsonAlias({"correo", "Correo"})
    private String correoContacto;

    private String telefono;
}