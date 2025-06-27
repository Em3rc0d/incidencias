package com.incidencias.service;

import com.incidencias.model.TipoIncidencia;
import com.incidencias.repository.TipoIncidenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoIncidenciaService implements ITipoIncidenciaService{

    @Autowired
    private TipoIncidenciaRepository tipoIncidenciaRepository;

    public List<TipoIncidencia> obtenerTodos() {
        return tipoIncidenciaRepository.findAll();
    }

    public Optional<TipoIncidencia> obtenerPorId(Long id) {
        return tipoIncidenciaRepository.findById(id);
    }

    public TipoIncidencia crear(TipoIncidencia tipoIncidencia) {
        return tipoIncidenciaRepository.save(tipoIncidencia);
    }

    public void eliminar(Long id) {
        tipoIncidenciaRepository.deleteById(id);
    }

    public boolean existePorNombre(String nombre) {
        return tipoIncidenciaRepository.existsByNombre(nombre);
    }
}
