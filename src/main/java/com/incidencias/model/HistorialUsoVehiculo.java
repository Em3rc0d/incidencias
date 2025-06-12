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
@Table(name = "historial_uso_vehiculo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistorialUsoVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "historial_uso_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "numero_vueltas")
    private Integer numeroVueltas;

    // Nuevo campo para el día (Ej: "Lunes", "Martes")
    @Column(name = "dia", length = 20)
    private String dia;

    // Nuevo campo para la hora (Ej: "08:00")
    @Column(name = "hora", length = 5)
    private String hora;
}
