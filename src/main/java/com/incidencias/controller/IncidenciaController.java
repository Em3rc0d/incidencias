package com.incidencias.controller;

import java.util.List;
import java.util.stream.Collectors;

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
    public Incidencia obtener(@PathVariable Long id) {
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

    @GetMapping("/empresa/{empresaId}")
    public List<Incidencia> obtenerPorEmpresaId(@PathVariable Long empresaId) {
        return incidenciaService.obtenerPorEmpresaId(empresaId);
    }

    @GetMapping("/user/{userId}")
    public List<Incidencia> obtenerPorUsuarioId(@PathVariable Long userId) {
        return incidenciaService.obtenerPorUsuarioId(userId);
    }

    private IncidenciaDTO mapToDTO(Incidencia i) {
    IncidenciaDTO dto = new IncidenciaDTO();
    dto.setId(i.getId());
    dto.setUsuarioNombre(i.getUsuario().getNombre());
    dto.setFechaReporte(i.getFechaReporte());
    dto.setEstado(i.getEstado());
    return dto;
}
    
    @GetMapping("/dashboard/incidencias-antiguas")
    public List<IncidenciaDTO> obtenerIncidenciasAntiguas() {
        return incidenciaService.obtenerIncidenciasAntiguasNoResueltas()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}
