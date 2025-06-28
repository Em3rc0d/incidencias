package com.incidencias.service;

import com.incidencias.dto.ReporteAseguradoraDTO;
import com.incidencias.model.ReporteAseguradora;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IReporteAseguradoraService {

    List<ReporteAseguradora> listar();

    Optional<ReporteAseguradora> obtenerPorId(Long id);

    ReporteAseguradora guardar(ReporteAseguradoraDTO dto);

    void eliminar(Long id);

    ResponseEntity<ReporteAseguradora> actualizar(Long id, ReporteAseguradora actualizado);
}
