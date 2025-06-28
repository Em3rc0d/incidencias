package com.incidencias.service;

import com.incidencias.model.HistorialIncidencia;

import java.util.List;
import java.util.Optional;

public interface IHistorialIncidenciaService {

    List<HistorialIncidencia> listar();

    Optional<HistorialIncidencia> obtenerPorId(Long id);

    HistorialIncidencia guardar(HistorialIncidencia historial);

    void eliminar(Long id);

    List<HistorialIncidencia> listarPorEmpresa(Long idEmpresa);
}
