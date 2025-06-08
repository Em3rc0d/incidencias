package com.incidencias.service;

import com.incidencias.model.ReporteAseguradora;
import com.incidencias.repository.ReporteAseguradoraRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReporteAseguradoraService {

    private final ReporteAseguradoraRepository repository;

    public ReporteAseguradoraService(ReporteAseguradoraRepository repository) {
        this.repository = repository;
    }

    public List<ReporteAseguradora> listar() {
        return repository.findAll();
    }

    public Optional<ReporteAseguradora> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public ReporteAseguradora guardar(ReporteAseguradora reporte) {
        return repository.save(reporte);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
