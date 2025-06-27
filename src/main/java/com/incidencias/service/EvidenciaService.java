package com.incidencias.service;

import com.incidencias.model.Evidencia;
import com.incidencias.repository.EvidenciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvidenciaService {

    private final EvidenciaRepository repository;

    public EvidenciaService(EvidenciaRepository repository) {
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
        boolean esNuevo = (evidencia.getId() == null);
        Evidencia guardado = repository.save(evidencia);
        FileMirrorUtil.logOperation("evidencia", esNuevo ? "insert" : "update", guardado);
        return guardado;
    }

    public void eliminar(Long id) {
        repository.findById(id).ifPresent(e -> FileMirrorUtil.logOperation("evidencia", "delete", e));
        repository.deleteById(id);
    }
}
