// Afectado.java
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
@Table(name = "afectado")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Afectado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "afectado_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "incidencia_id")
    private Incidencia incidencia;

    private String nombre;

    @Column(name = "documento_identidad")
    private String documentoIdentidad;

    @Column(name = "tipo_tercero")
    private String tipoTercero;

    private String contacto;

    @Column(name = "descripcion_danio")
    private String descripcionDanio;
}
