package com.incidencias.service;

import java.util.List;

import com.incidencias.dto.IncidenciaDTO;
import com.incidencias.model.Incidencia;

public interface IncidenciaService {
    Incidencia crear(IncidenciaDTO dto);
    IncidenciaDTO actualizar(Long id, IncidenciaDTO dto);
    Incidencia obtenerPorId(Long id);
    List<Incidencia> listarTodos();
    void eliminar(Long id);
    List<Incidencia> obtenerPorEmpresaId(Long empresaId);
    List<Incidencia> obtenerPorUsuarioId(Long userId);
    List<Incidencia> obtenerIncidenciasAntiguasNoResueltas(Long empresaId);
}
