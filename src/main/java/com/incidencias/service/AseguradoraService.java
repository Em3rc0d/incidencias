// AseguradoraService.java
package com.incidencias.service;

import com.incidencias.dto.AseguradoraDTO;

import java.util.List;

public interface AseguradoraService {
    List<AseguradoraDTO> listar();
    AseguradoraDTO obtenerPorId(Long id);
    AseguradoraDTO crear(AseguradoraDTO dto);
    AseguradoraDTO actualizar(Long id, AseguradoraDTO dto);
    void eliminar(Long id);
}
