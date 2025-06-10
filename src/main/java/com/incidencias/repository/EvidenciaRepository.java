package com.incidencias.repository;

import com.incidencias.model.Evidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EvidenciaRepository extends JpaRepository<Evidencia, Long> {

    @Query("SELECT e FROM Evidencia e WHERE e.incidencia.id = :incidenceId")
    List<Evidencia> findByIncidenceId(Long incidenceId);

}
