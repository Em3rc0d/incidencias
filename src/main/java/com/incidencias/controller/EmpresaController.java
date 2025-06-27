// EmpresaController.java
package com.incidencias.controller;

import com.incidencias.dto.EmpresaDTO;
import com.incidencias.model.Empresa;
import com.incidencias.service.IEmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    @Autowired
    private IEmpresaService service;

    @GetMapping
    public List<Empresa> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Empresa obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public EmpresaDTO crear(@RequestBody EmpresaDTO dto) {
        return service.crear(dto);
    }

    @PutMapping("/{id}")
    public EmpresaDTO actualizar(@PathVariable Long id, @RequestBody EmpresaDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
