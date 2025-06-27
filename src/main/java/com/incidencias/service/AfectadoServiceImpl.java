package com.incidencias.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incidencias.dto.AfectadoDTO;
import com.incidencias.model.Afectado;
import com.incidencias.model.Incidencia;
import com.incidencias.repository.AfectadoRepository;
import com.incidencias.repository.IncidenciaRepository;

@Service
public class AfectadoServiceImpl implements IAfectadoService {

    @Autowired
    private AfectadoRepository repository;

    @Autowired
    private IncidenciaRepository incidenciaRepository;

    private AfectadoDTO toDTO(Afectado entity) {
        AfectadoDTO dto = new AfectadoDTO();
        dto.setIncidenciaId(entity.getIncidencia() != null ? entity.getIncidencia().getId() : null);
        dto.setNombre(entity.getNombre());
        dto.setDocumentoIdentidad(entity.getDocumentoIdentidad());
        dto.setTipoTercero(entity.getTipoTercero());
        dto.setContacto(entity.getContacto());
        dto.setDescripcionDanio(entity.getDescripcionDanio());
        return dto;
    }

    private Afectado toEntity(AfectadoDTO dto) {
        Afectado entity = new Afectado();
        if (dto.getIncidenciaId() != null) {
            Optional<Incidencia> incidencia = incidenciaRepository.findById(dto.getIncidenciaId());
            incidencia.ifPresent(entity::setIncidencia);
        }
        entity.setNombre(dto.getNombre());
        entity.setDocumentoIdentidad(dto.getDocumentoIdentidad());
        entity.setTipoTercero(dto.getTipoTercero());
        entity.setContacto(dto.getContacto());
        entity.setDescripcionDanio(dto.getDescripcionDanio());
        return entity;
    }

    @Override
    public AfectadoDTO crear(AfectadoDTO dto) {
        Afectado guardado = repository.save(toEntity(dto));
        FileMirrorUtil.logOperation("afectado", "insert", guardado); // <- Aquí
        return toDTO(guardado);
    }

    @Override
    public AfectadoDTO obtenerPorId(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
    }
    
    @Override
    public List<Afectado> obtenerPorIncidenciaId(Long incidenciaId){
        return repository.findByIncidenciaId(incidenciaId);
    }

    @Override
    public List<AfectadoDTO> listarTodos() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public AfectadoDTO actualizar(Long id, AfectadoDTO dto) {
        Optional<Afectado> optional = repository.findById(id);
        if (optional.isPresent()) {
            Afectado entity = optional.get();
            entity.setNombre(dto.getNombre());
            entity.setDocumentoIdentidad(dto.getDocumentoIdentidad());
            entity.setTipoTercero(dto.getTipoTercero());
            entity.setContacto(dto.getContacto());
            entity.setDescripcionDanio(dto.getDescripcionDanio());
            if (dto.getIncidenciaId() != null) {
                incidenciaRepository.findById(dto.getIncidenciaId()).ifPresent(entity::setIncidencia);
            }
            FileMirrorUtil.logOperation("afectado", "update", entity);
            return toDTO(repository.save(entity));
        }
        return null;
    }

    @Override
    public void eliminar(Long id) {
        repository.findById(id).ifPresent(a -> FileMirrorUtil.logOperation("afectado", "delete", a));
        repository.deleteById(id);
    }
}
