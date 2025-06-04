// HistorialIncidencia.java
package com.incidencias.model;

import java.time.LocalDateTime;

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
@Table(name = "historial_incidencia")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistorialIncidencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "historial_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "incidencia_id")
    private Incidencia incidencia;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String estado;
    private String observacion;
    private LocalDateTime fecha;
}