// AseguradoraServiceImpl.java
package com.incidencias.service;

import com.incidencias.dao.AseguradoraDAO;
import com.incidencias.dto.AseguradoraDTO;
import com.incidencias.model.Aseguradora;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AseguradoraServiceImpl implements IAseguradoraService {

    @Autowired
    private AseguradoraDAO dao;

    private AseguradoraDTO toDTO(Aseguradora a) {
        AseguradoraDTO dto = new AseguradoraDTO();
        dto.setId(a.getId());
        dto.setNombre(a.getNombre());
        dto.setCorreoContacto(a.getCorreoContacto());
        dto.setTelefono(a.getTelefono());
        return dto;
    }

    private Aseguradora toEntity(AseguradoraDTO dto) {
        Aseguradora a = new Aseguradora();
        a.setId(dto.getId());
        a.setNombre(dto.getNombre());
        a.setCorreoContacto(dto.getCorreoContacto());
        a.setTelefono(dto.getTelefono());
        return a;
    }

    @Override
    public List<AseguradoraDTO> listar() {
        return dao.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public AseguradoraDTO obtenerPorId(Long id) {
        return dao.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public AseguradoraDTO crear(AseguradoraDTO dto) {
        Aseguradora nueva = dao.save(toEntity(dto));
        FileMirrorUtil.logOperation("aseguradora", "insert", nueva); // Log mirror
        return toDTO(nueva);
    }

    @Override
    public AseguradoraDTO actualizar(Long id, AseguradoraDTO dto) {
        Aseguradora existente = dao.findById(id).orElseThrow();
        existente.setNombre(dto.getNombre());
        existente.setCorreoContacto(dto.getCorreoContacto());
        existente.setTelefono(dto.getTelefono());
        Aseguradora actualizada = dao.save(existente);
        FileMirrorUtil.logOperation("aseguradora", "update", actualizada); // Log mirror
        return toDTO(actualizada);
    }

    @Override
    public void eliminar(Long id) {
        dao.findById(id).ifPresent(a -> FileMirrorUtil.logOperation("aseguradora", "delete", a)); // Pre-log
        dao.deleteById(id);
    }
}
