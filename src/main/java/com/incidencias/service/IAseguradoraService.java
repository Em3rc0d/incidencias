package com.incidencias.service;

import com.incidencias.dto.AseguradoraDTO;
import com.incidencias.model.Aseguradora;

import java.util.List;

public interface IAseguradoraService {
List<Aseguradora> listar();
    Aseguradora obtenerPorId(Long id);
    Aseguradora crear(AseguradoraDTO dto);
    Aseguradora actualizar(Long id, AseguradoraDTO dto);
    void eliminar(Long id);
}