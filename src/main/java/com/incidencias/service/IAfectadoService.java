package com.incidencias.service;

import java.util.List;

import com.incidencias.dto.AfectadoDTO;
import com.incidencias.model.Afectado;

public interface IAfectadoService {
    AfectadoDTO crear(AfectadoDTO dto);
    AfectadoDTO obtenerPorId(Long id);
    List<Afectado> obtenerPorIncidenciaId(Long incidenciaId);
    List<AfectadoDTO> listarTodos();
    AfectadoDTO actualizar(Long id, AfectadoDTO dto);
    void eliminar(Long id);
}
