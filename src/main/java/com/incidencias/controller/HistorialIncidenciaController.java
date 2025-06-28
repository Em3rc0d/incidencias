package com.incidencias.controller;

import com.incidencias.model.HistorialIncidencia;
import com.incidencias.service.HistorialIncidenciaServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historial-incidencias")
public class HistorialIncidenciaController {

    private final HistorialIncidenciaServiceImpl service;

    public HistorialIncidenciaController(HistorialIncidenciaServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public List<HistorialIncidencia> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialIncidencia> obtener(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public HistorialIncidencia crear(@RequestBody HistorialIncidencia historial) {
        return service.guardar(historial);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistorialIncidencia> actualizar(@PathVariable Long id, @RequestBody HistorialIncidencia actualizado) {
        return service.obtenerPorId(id)
                .map(h -> {
                    actualizado.setId(h.getId());
                    return ResponseEntity.ok(service.guardar(actualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.obtenerPorId(id).isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/empresa/{id}")
    public ResponseEntity<List<HistorialIncidencia>> listarPorEmpresa(@PathVariable Long id){
        return ResponseEntity.ok(service.listarPorEmpresa(id));
    }
}
