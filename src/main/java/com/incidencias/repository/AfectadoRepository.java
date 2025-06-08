package com.incidencias.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incidencias.model.Afectado;

public interface AfectadoRepository extends JpaRepository<Afectado, Long> {
}
