// EmpresaService.java
package com.incidencias.service;

import java.util.List;

import com.incidencias.model.Empresa;
import org.springframework.stereotype.Service;

import com.incidencias.dto.EmpresaDTO;
@Service
public interface IEmpresaService {
    List<Empresa> listar();
    Empresa obtenerPorId(Long id);
    EmpresaDTO crear(EmpresaDTO dto);
    EmpresaDTO actualizar(Long id, EmpresaDTO dto);
    void eliminar(Long id);
}
