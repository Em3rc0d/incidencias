package com.incidencias.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.incidencias.model.Incidencia;

@Repository
public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {

    @Query("SELECT i FROM Incidencia i WHERE i.usuario.empresa.id = :empresaId")
    List<Incidencia> findByEmpresaId(Long empresaId);

    @Query("SELECT i FROM Incidencia i WHERE i.usuario.id = :userId")
    List<Incidencia> findByUsuarioId(Long userId);
}
