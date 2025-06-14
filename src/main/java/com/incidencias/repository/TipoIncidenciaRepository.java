package com.incidencias.repository;

import com.incidencias.model.TipoIncidencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoIncidenciaRepository extends JpaRepository<TipoIncidencia, Long> {
    boolean existsByNombre(String nombre);
}
