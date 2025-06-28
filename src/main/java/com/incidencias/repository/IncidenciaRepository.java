package com.incidencias.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
                        AND u.empresa_id = :empresaId
                        GROUP BY u.nombre
                        ORDER BY cantidad DESC
                        LIMIT 5
                        """, nativeQuery = true)
        List<Object[]> topChoferesConMasIncidencias(@Param("empresaId") Long empresaId);

        @Query("SELECT i FROM Incidencia i WHERE i.estado <> 'CERRADA' AND i.fechaReporte < :fechaLimite AND i.usuario.empresa.id = :empresaId")
        List<Incidencia> findIncidenciasNoResueltasMas7Dias(@Param("fechaLimite") LocalDateTime fechaLimite,
                        @Param("empresaId") Long empresaId);

        @Query(value = """
                        SELECT i.incidencia_id, i.descripcion, u.nombre, i.fecha_reporte, COUNT(a.afectado_id) AS total
                        FROM incidencia i
                        JOIN usuario u ON i.usuario_id = u.usuario_id
                        JOIN afectado a ON a.incidencia_id = i.incidencia_id
                        WHERE u.empresa_id = :empresaId
                        GROUP BY i.incidencia_id, i.descripcion, u.nombre, i.fecha_reporte
                        ORDER BY total DESC
                        LIMIT 5
                        """, nativeQuery = true)
        List<Object[]> topIncidenciasConMasAfectados(@Param("empresaId") Long empresaId);

        @Query(value = """
                            SELECT
                                e.nombre AS nombre_empresa,
                                COUNT(i.incidencia_id) AS total,
                                SUM(CASE WHEN i.estado = 'abierta' THEN 1 ELSE 0 END) AS abiertas,
                                SUM(CASE WHEN i.estado = 'pendiente' THEN 1 ELSE 0 END) AS pendientes,
                                SUM(CASE WHEN i.estado = 'cerrada' THEN 1 ELSE 0 END) AS cerradas
                            FROM empresa e
                            JOIN vehiculo v ON e.empresa_id = v.empresa_id
                            JOIN incidencia i ON v.vehiculo_id = i.vehiculo_id
                            WHERE e.empresa_id = :empresaId
                            GROUP BY e.nombre
                        """, nativeQuery = true)
        List<Object[]> contarIncidenciasPorEmpresa(@Param("empresaId") Long empresaId);

}
