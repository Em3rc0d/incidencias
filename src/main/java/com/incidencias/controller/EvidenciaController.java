package com.incidencias.controller;

import com.incidencias.model.Evidencia;
import com.incidencias.service.EvidenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evidencias")
public class EvidenciaController {

    private final EvidenciaService service;

    public EvidenciaController(EvidenciaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Evidencia> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evidencia> obtener(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Evidencia crear(@RequestBody Evidencia evidencia) {
        return service.guardar(evidencia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evidencia> actualizar(@PathVariable Long id, @RequestBody Evidencia actualizada) {
        return service.obtenerPorId(id)
                .map(e -> {
                    actualizada.setId(e.getId());
                    return ResponseEntity.ok(service.guardar(actualizada));
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
}
