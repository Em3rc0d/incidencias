package com.incidencias.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incidencias.dto.AfectadoDTO;
import com.incidencias.model.Afectado;
import com.incidencias.model.Incidencia;
import com.incidencias.repository.AfectadoRepository;
import com.incidencias.repository.IncidenciaRepository;
import com.incidencias.service.IAfectadoService;

@Service
public class AfectadoServiceImpl implements IAfectadoService {

    @Autowired
    private AfectadoRepository afectadoRepo;

    @Autowired
    private IncidenciaRepository incidenciaRepo;

    private AfectadoDTO toDTO(Afectado a) {
        AfectadoDTO dto = new AfectadoDTO();
        dto.setId(a.getId());
        dto.setIncidenciaId(a.getIncidencia() != null ? a.getIncidencia().getId() : null);
        dto.setNombre(a.getNombre());
        dto.setDocumentoIdentidad(a.getDocumentoIdentidad());
        dto.setTipoTercero(a.getTipoTercero());
        dto.setContacto(a.getContacto());
        dto.setDescripcionDanio(a.getDescripcionDanio());
        return dto;
    }

    private Afectado toEntity(AfectadoDTO dto) {
        Afectado a = new Afectado();
        a.setId(dto.getId());
        a.setNombre(dto.getNombre());
        a.setDocumentoIdentidad(dto.getDocumentoIdentidad());
        a.setTipoTercero(dto.getTipoTercero());
        a.setContacto(dto.getContacto());
        a.setDescripcionDanio(dto.getDescripcionDanio());

        if (dto.getIncidenciaId() != null) {
            Incidencia incidencia = incidenciaRepo.findById(dto.getIncidenciaId()).orElse(null);
            a.setIncidencia(incidencia);
        }

        return a;
    }

    @Override
    public AfectadoDTO crear(AfectadoDTO dto) {
        Afectado nuevo = afectadoRepo.save(toEntity(dto));
        return toDTO(nuevo);
    }

    @Override
    public AfectadoDTO obtenerPorId(Long id) {
        return afectadoRepo.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public List<AfectadoDTO> listarTodos() {
        return afectadoRepo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public AfectadoDTO actualizar(Long id, AfectadoDTO dto) {
        Afectado existente = afectadoRepo.findById(id).orElse(null);
        if (existente == null) return null;

        existente.setNombre(dto.getNombre());
        existente.setDocumentoIdentidad(dto.getDocumentoIdentidad());
        existente.setTipoTercero(dto.getTipoTercero());
        existente.setContacto(dto.getContacto());
        existente.setDescripcionDanio(dto.getDescripcionDanio());

        if (dto.getIncidenciaId() != null) {
            Incidencia incidencia = incidenciaRepo.findById(dto.getIncidenciaId()).orElse(null);
            existente.setIncidencia(incidencia);
        }

        return toDTO(afectadoRepo.save(existente));
    }

    @Override
    public void eliminar(Long id) {
        afectadoRepo.deleteById(id);
    }
}
