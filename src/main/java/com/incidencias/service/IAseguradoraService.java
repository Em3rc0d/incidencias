// AseguradoraService.java
package com.incidencias.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.incidencias.dto.AseguradoraDTO;
@Service
public interface IAseguradoraService {
    List<AseguradoraDTO> listar();
    AseguradoraDTO obtenerPorId(Long id);
    AseguradoraDTO crear(AseguradoraDTO dto);
    AseguradoraDTO actualizar(Long id, AseguradoraDTO dto);
    void eliminar(Long id);
}
