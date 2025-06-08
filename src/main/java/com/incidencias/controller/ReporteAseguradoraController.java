package com.incidencias.controller;

import com.incidencias.model.ReporteAseguradora;
import com.incidencias.service.ReporteAseguradoraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes-aseguradora")
public class ReporteAseguradoraController {

    private final ReporteAseguradoraService service;

    public ReporteAseguradoraController(ReporteAseguradoraService service) {
        this.service = service;
    }

    @GetMapping
    public List<ReporteAseguradora> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReporteAseguradora> obtener(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ReporteAseguradora crear(@RequestBody ReporteAseguradora reporte) {
        return service.guardar(reporte);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReporteAseguradora> actualizar(@PathVariable Long id, @RequestBody ReporteAseguradora actualizado) {
        return service.obtenerPorId(id)
                .map(r -> {
                    actualizado.setId(r.getId());
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
}
