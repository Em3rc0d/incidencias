package com.incidencias.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.incidencias.model.Afectado;

public interface AfectadoRepository extends JpaRepository<Afectado, Long> {
@Query("SELECT e FROM Afectado e WHERE e.incidencia.id = :incidenciaId")
    List<Afectado> findByIncidenciaId(Long incidenciaId);
}
