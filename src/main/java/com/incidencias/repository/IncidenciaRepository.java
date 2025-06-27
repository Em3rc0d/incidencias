package com.incidencias.repository;

import java.util.List;
import java.util.Map;

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

    @Query(value = """
    SELECT u.nombre, COUNT(i.incidencia_id) AS cantidad
    FROM usuario u
    JOIN incidencia i ON u.usuario_id = i.usuario_id
    WHERE LOWER(u.rol) = 'chofer'
    GROUP BY u.nombre   
    ORDER BY cantidad DESC
    LIMIT 5
    """, nativeQuery = true)
    List<Object[]> topChoferesConMasIncidencias();

}
