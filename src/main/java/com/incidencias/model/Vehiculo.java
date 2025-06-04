// Vehiculo.java
package com.incidencias.model;

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
@Table(name = "vehiculo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehiculo_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @Column(nullable = false, unique = true)
    private String placa;

    private String marca;
    private String modelo;
    private Integer anio;
    private String tipo;

    @Column(columnDefinition = "varchar(20) default 'activo'")
    private String estado;
}