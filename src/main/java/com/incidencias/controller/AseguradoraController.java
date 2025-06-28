// AseguradoraController.java
package com.incidencias.controller;

import com.incidencias.dto.AseguradoraDTO;
import com.incidencias.service.IAseguradoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aseguradoras")
public class AseguradoraController {

    @Autowired
    private IAseguradoraService service;

    @GetMapping
    public List<AseguradoraDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public AseguradoraDTO obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public AseguradoraDTO crear(@RequestBody AseguradoraDTO dto) {
        return service.crear(dto);
    }

    @PutMapping("/{id}")
    public AseguradoraDTO actualizar(@PathVariable Long id, @RequestBody AseguradoraDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
