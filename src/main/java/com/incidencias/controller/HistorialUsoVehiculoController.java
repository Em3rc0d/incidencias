package com.incidencias.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incidencias.model.HistorialUsoVehiculo;
import com.incidencias.service.HistorialUsoVehiculoServiceImpl;

@RestController
@RequestMapping("/api/historial-uso")
public class HistorialUsoVehiculoController {

    private final HistorialUsoVehiculoServiceImpl service;

    public HistorialUsoVehiculoController(HistorialUsoVehiculoServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public List<HistorialUsoVehiculo> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialUsoVehiculo> obtener(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public HistorialUsoVehiculo crear(@RequestBody HistorialUsoVehiculo historial) {
        return service.guardar(historial);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistorialUsoVehiculo> actualizar(@PathVariable Long id, @RequestBody HistorialUsoVehiculo actualizado) {
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
}
