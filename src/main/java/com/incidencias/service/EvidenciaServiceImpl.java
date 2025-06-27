package com.incidencias.service;

import com.incidencias.model.Evidencia;
import com.incidencias.repository.EvidenciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvidenciaServiceImpl implements IEvidenciaService{

    private final EvidenciaRepository repository;

    public EvidenciaServiceImpl(EvidenciaRepository repository) {
        this.repository = repository;
    }

    public List<Evidencia> listar() {
        return repository.findAll();
    }

    public Optional<Evidencia> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public List<Evidencia> obtenerPorIncidencia(Long incidenciaId) {
        return repository.findByIncidenceId(incidenciaId);
    }

    public Evidencia guardar(Evidencia evidencia) {
        return repository.save(evidencia);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
