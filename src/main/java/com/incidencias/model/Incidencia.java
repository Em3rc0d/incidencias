// Incidencia.java
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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "incidencia")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Incidencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "incidencia_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "tipo_incidencia_id")
    private TipoIncidencia tipoIncidencia;

    @Column(nullable = false)
    private String descripcion;

    private String prioridad;

    @Builder.Default
    private String estado = "Abierta";

    @Column(name = "fecha_reporte")
    private LocalDateTime fechaReporte;

    private Double latitud;
    private Double longitud;
}
