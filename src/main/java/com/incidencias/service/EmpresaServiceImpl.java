// EmpresaServiceImpl.java
package com.incidencias.service;

import com.incidencias.dao.EmpresaDAO;
import com.incidencias.dao.AseguradoraDAO;
import com.incidencias.dto.EmpresaDTO;
import com.incidencias.model.Empresa;
import com.incidencias.model.Aseguradora;
import com.incidencias.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaDAO empresaDAO;

    @Autowired
    private AseguradoraDAO aseguradoraDAO;

    private EmpresaDTO toDTO(Empresa e) {
        EmpresaDTO dto = new EmpresaDTO();
        dto.setId(e.getId());
        dto.setNombre(e.getNombre());
        dto.setRuc(e.getRuc());
        dto.setAseguradoraId(e.getAseguradora() != null ? e.getAseguradora().getId() : null);
        return dto;
    }

    private Empresa toEntity(EmpresaDTO dto) {
        Empresa e = new Empresa();
        e.setId(dto.getId());
        e.setNombre(dto.getNombre());
        e.setRuc(dto.getRuc());
        if (dto.getAseguradoraId() != null) {
            Aseguradora a = aseguradoraDAO.findById(dto.getAseguradoraId()).orElse(null);
            e.setAseguradora(a);
        }
        return e;
    }

    @Override
    public List<EmpresaDTO> listar() {
        return empresaDAO.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public EmpresaDTO obtenerPorId(Long id) {
        return empresaDAO.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public EmpresaDTO crear(EmpresaDTO dto) {
        Empresa nueva = empresaDAO.save(toEntity(dto));
        return toDTO(nueva);
    }

    @Override
    public EmpresaDTO actualizar(Long id, EmpresaDTO dto) {
        Empresa existente = empresaDAO.findById(id).orElseThrow();
        existente.setNombre(dto.getNombre());
        existente.setRuc(dto.getRuc());
        if (dto.getAseguradoraId() != null) {
            Aseguradora a = aseguradoraDAO.findById(dto.getAseguradoraId()).orElse(null);
            existente.setAseguradora(a);
        }
        return toDTO(empresaDAO.save(existente));
    }

    @Override
    public void eliminar(Long id) {
        empresaDAO.deleteById(id);
    }
}
