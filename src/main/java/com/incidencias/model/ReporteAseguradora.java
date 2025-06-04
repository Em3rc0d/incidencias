// ReporteAseguradora.java
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
@Table(name = "reporte_aseguradora")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteAseguradora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reporte_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "incidencia_id")
    private Incidencia incidencia;

    @ManyToOne
    @JoinColumn(name = "aseguradora_id")
    private Aseguradora aseguradora;

    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio;

    @Column(name = "estado_envio")
    private String estadoEnvio;

    private String asunto;
    private String cuerpo;
    private String observaciones;
}