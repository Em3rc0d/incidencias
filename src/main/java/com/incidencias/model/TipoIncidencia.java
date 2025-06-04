// TipoIncidencia.java
package com.incidencias.model;

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
@Table(name = "tipo_incidencia")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoIncidencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipo_incidencia_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    private String descripcion;
}
