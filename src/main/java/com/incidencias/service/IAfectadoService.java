package com.incidencias.service;

import java.util.List;

import com.incidencias.dto.AfectadoDTO;

public interface IAfectadoService {
    AfectadoDTO crear(AfectadoDTO dto);
    AfectadoDTO obtenerPorId(Long id);
    List<AfectadoDTO> listarTodos();
    AfectadoDTO actualizar(Long id, AfectadoDTO dto);
    void eliminar(Long id);
}
