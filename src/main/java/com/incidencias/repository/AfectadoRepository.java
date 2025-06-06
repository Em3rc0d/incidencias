package com.incidencias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.incidencias.model.Afectado;

@Repository
public interface AfectadoRepository extends JpaRepository<Afectado, Long> {
}
