package com.incidencias.controller;

import java.util.List;

import com.incidencias.model.Incidencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.incidencias.dto.IncidenciaDTO;
import com.incidencias.service.IncidenciaService;

@RestController
@RequestMapping("/api/incidencias")
public class IncidenciaController {

    @Autowired
    private IncidenciaService incidenciaService;

    @PostMapping
    public Incidencia crear(@RequestBody IncidenciaDTO dto) {
        return incidenciaService.crear(dto);
    }

    @PutMapping("/{id}")
    public IncidenciaDTO actualizar(@PathVariable Long id, @RequestBody IncidenciaDTO dto) {
        return incidenciaService.actualizar(id, dto);
    }

    @GetMapping("/{id}")
    public IncidenciaDTO obtener(@PathVariable Long id) {
        return incidenciaService.obtenerPorId(id);
    }

    @GetMapping
    public List<Incidencia> listar() {
        return incidenciaService.listarTodos();
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        incidenciaService.eliminar(id);
    }
}
