// EmpresaService.java
package com.incidencias.service;

import com.incidencias.dto.EmpresaDTO;

import java.util.List;

public interface EmpresaService {
    List<EmpresaDTO> listar();
    EmpresaDTO obtenerPorId(Long id);
    EmpresaDTO crear(EmpresaDTO dto);
    EmpresaDTO actualizar(Long id, EmpresaDTO dto);
    void eliminar(Long id);
}
