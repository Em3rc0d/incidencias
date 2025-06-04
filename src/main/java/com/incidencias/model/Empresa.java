// Empresa.java
package com.incidencias.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empresa")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empresa_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aseguradora_id")
    private Aseguradora aseguradora;

    @Column(nullable = false, unique = true)
    private String nombre;

    @JsonAlias({"RUC", "ruc"})
    private String ruc;
}