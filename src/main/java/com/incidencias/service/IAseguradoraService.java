package com.incidencias.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.incidencias.dto.AseguradoraDTO;
import com.incidencias.model.Aseguradora;
@Service
public interface IAseguradoraService {
List<Aseguradora> listar();
    Aseguradora obtenerPorId(Long id);
    Aseguradora crear(AseguradoraDTO dto);
    Aseguradora actualizar(Long id, AseguradoraDTO dto);
    void eliminar(Long id);
}