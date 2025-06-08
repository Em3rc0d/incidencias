// EmpresaService.java
package com.incidencias.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.incidencias.dto.EmpresaDTO;
@Service
public interface EmpresaService {
    List<EmpresaDTO> listar();
    EmpresaDTO obtenerPorId(Long id);
    EmpresaDTO crear(EmpresaDTO dto);
    EmpresaDTO actualizar(Long id, EmpresaDTO dto);
    void eliminar(Long id);
}
