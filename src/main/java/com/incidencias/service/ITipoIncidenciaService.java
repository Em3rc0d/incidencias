package com.incidencias.service;

import com.incidencias.model.TipoIncidencia;

import java.util.List;
import java.util.Optional;

public interface ITipoIncidenciaService {

    List<TipoIncidencia> obtenerTodos();

    Optional<TipoIncidencia> obtenerPorId(Long id);

    TipoIncidencia crear(TipoIncidencia tipoIncidencia);

    void eliminar(Long id);

    boolean existePorNombre(String nombre);
}
