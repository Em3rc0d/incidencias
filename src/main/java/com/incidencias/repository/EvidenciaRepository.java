package com.incidencias.repository;

import com.incidencias.model.Evidencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvidenciaRepository extends JpaRepository<Evidencia, Long> {
}
