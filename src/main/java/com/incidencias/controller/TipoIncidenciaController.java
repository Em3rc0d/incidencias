package com.incidencias.controller;

import com.incidencias.model.TipoIncidencia;
import com.incidencias.service.TipoIncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-incidencia")
public class TipoIncidenciaController {

    @Autowired
    private TipoIncidenciaService tipoIncidenciaService;

    @GetMapping
    public ResponseEntity<List<TipoIncidencia>> listarTodos() {
        return ResponseEntity.ok(tipoIncidenciaService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoIncidencia> obtenerPorId(@PathVariable Long id) {
        return tipoIncidenciaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoIncidencia> crear(@RequestBody TipoIncidencia tipoIncidencia) {
        if (tipoIncidenciaService.existePorNombre(tipoIncidencia.getNombre())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tipoIncidenciaService.crear(tipoIncidencia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tipoIncidenciaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
