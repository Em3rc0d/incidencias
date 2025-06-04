package com.incidencias.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incidencias.model.Vehiculo;
import com.incidencias.service.VehiculoService;

// --- Controller Vehiculo ---
@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {
    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping
    public List<Vehiculo> listar() {
        return vehiculoService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> obtener(@PathVariable Long id) {
        return vehiculoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Vehiculo guardar(@RequestBody Vehiculo vehiculo) {
        return vehiculoService.guardar(vehiculo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        vehiculoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}