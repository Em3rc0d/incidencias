package com.incidencias.service;

import com.incidencias.model.HistorialIncidencia;
import com.incidencias.repository.HistorialIncidenciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistorialIncidenciaService {

    private final HistorialIncidenciaRepository repository;

    public HistorialIncidenciaService(HistorialIncidenciaRepository repository) {
        this.repository = repository;
    }

    public List<HistorialIncidencia> listar() {
        return repository.findAll();
    }

    public Optional<HistorialIncidencia> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public HistorialIncidencia guardar(HistorialIncidencia historial) {
        return repository.save(historial);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public List<HistorialIncidencia> listarPorEmpresa(Long idEmpresa) {
        return repository.findAllByEmpresa(idEmpresa);
    }
}
