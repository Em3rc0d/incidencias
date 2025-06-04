// Evidencia.java
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
@Table(name = "evidencia")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evidencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evidencia_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "incidencia_id")
    private Incidencia incidencia;

    @Column(name = "url_archivo", nullable = false)
    private String urlArchivo;

    @Column(name = "tipo_archivo")
    private String tipoArchivo;

    @Column(name = "fecha_subida")
    private LocalDateTime fechaSubida;
}
