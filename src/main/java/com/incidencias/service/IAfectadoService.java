package com.incidencias.service;

import com.incidencias.dto.AfectadoDTO;
import com.incidencias.model.Afectado;

import java.util.List;

public interface IAfectadoService {
    AfectadoDTO crear(AfectadoDTO dto);
    AfectadoDTO obtenerPorId(Long id);
    List<AfectadoDTO> listarTodos();
    AfectadoDTO actualizar(Long id, AfectadoDTO dto);
    void eliminar(Long id);
}
