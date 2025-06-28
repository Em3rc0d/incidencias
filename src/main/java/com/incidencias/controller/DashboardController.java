package com.incidencias.controller;

import com.incidencias.dto.ChoferIncidenciasDTO;
import com.incidencias.dto.EmpresaIncidenciasDTO;
import com.incidencias.dto.IncidenciaAfectadosDTO;
import com.incidencias.service.IAgregadoDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private IAgregadoDashboardService dashboardService;

    @GetMapping("/top-choferes")
    public List<ChoferIncidenciasDTO> obtenerTopChoferes(@RequestParam Long empresaId) {
        return dashboardService.obtenerTopChoferesConMasIncidencias(empresaId);
    }

    @GetMapping("/top-incidencias-afectados")
    public List<IncidenciaAfectadosDTO> obtenerTopIncidenciasAfectados(@RequestParam Long empresaId) {
        return dashboardService.obtenerTopIncidenciasConMasAfectados(empresaId);
    }

    @GetMapping("/incidencias-por-empresa")
    public List<EmpresaIncidenciasDTO> incidenciasPorEmpresa(@RequestParam Long empresaId) {
        return dashboardService.obtenerIncidenciasPorEmpresa(empresaId);
    }
}
