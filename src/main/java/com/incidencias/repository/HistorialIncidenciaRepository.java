package com.incidencias.repository;

import com.incidencias.model.HistorialIncidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistorialIncidenciaRepository extends JpaRepository<HistorialIncidencia, Long> {

    @Query("SELECT h FROM HistorialIncidencia h WHERE h.usuario.empresa.id = :idEmpresa")
    List<HistorialIncidencia> findAllByEmpresa(Long idEmpresa);
}
