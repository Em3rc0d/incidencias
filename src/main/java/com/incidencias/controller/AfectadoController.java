package com.incidencias.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incidencias.dto.AfectadoDTO;
import com.incidencias.service.IAfectadoService;

@RestController
@RequestMapping("/api/afectados")
public class AfectadoController {

    @Autowired
    private IAfectadoService service;

    @PostMapping
    public AfectadoDTO crear(@RequestBody AfectadoDTO dto) {
        return service.crear(dto);
    }

    @GetMapping("/{id}")
    public AfectadoDTO obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @GetMapping
    public List<AfectadoDTO> listarTodos() {
        return service.listarTodos();
    }

    @PutMapping("/{id}")
    public AfectadoDTO actualizar(@PathVariable Long id, @RequestBody AfectadoDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
