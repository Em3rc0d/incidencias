package com.incidencias.service;

import java.util.List;
import com.incidencias.dto.IncidenciaDTO;
import com.incidencias.model.Incidencia;

public interface IncidenciaService {
    Incidencia crear(IncidenciaDTO dto);
    IncidenciaDTO actualizar(Long id, IncidenciaDTO dto);
    IncidenciaDTO obtenerPorId(Long id);
    List<Incidencia> listarTodos();
    void eliminar(Long id);
}
