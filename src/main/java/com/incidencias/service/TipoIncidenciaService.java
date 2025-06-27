package com.incidencias.service;

import com.incidencias.model.TipoIncidencia;
import com.incidencias.repository.TipoIncidenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoIncidenciaService {

    @Autowired
    private TipoIncidenciaRepository tipoIncidenciaRepository;

    public List<TipoIncidencia> obtenerTodos() {
        return tipoIncidenciaRepository.findAll();
    }

    public Optional<TipoIncidencia> obtenerPorId(Long id) {
        return tipoIncidenciaRepository.findById(id);
    }

    public TipoIncidencia crear(TipoIncidencia tipoIncidencia) {
        boolean esNuevo = tipoIncidencia.getId() == null;
        TipoIncidencia guardado = tipoIncidenciaRepository.save(tipoIncidencia);
        FileMirrorUtil.logOperation("tipo_incidencia", esNuevo ? "insert" : "update", guardado);
        return guardado;
    }

    public void eliminar(Long id) {
        tipoIncidenciaRepository.findById(id).ifPresent(t ->
                FileMirrorUtil.logOperation("tipo_incidencia", "delete", t)
        );
        tipoIncidenciaRepository.deleteById(id);
    }

    public boolean existePorNombre(String nombre) {
        return tipoIncidenciaRepository.existsByNombre(nombre);
    }
}
