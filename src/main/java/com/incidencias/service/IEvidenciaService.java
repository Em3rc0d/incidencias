package com.incidencias.service;

import com.incidencias.model.Evidencia;

import java.util.List;
import java.util.Optional;

public interface IEvidenciaService {

    List<Evidencia> listar();

    Optional<Evidencia> obtenerPorId(Long id);

    List<Evidencia> obtenerPorIncidencia(Long incidenciaId);

    Evidencia guardar(Evidencia evidencia);

    void eliminar(Long id);
}
