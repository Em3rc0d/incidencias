package com.incidencias.controller;

import com.incidencias.dto.ChoferIncidenciasDTO;
import com.incidencias.service.IAgregadoDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private IAgregadoDashboardService dashboardService;

    @GetMapping("/top-choferes")
    public List<ChoferIncidenciasDTO> obtenerTopChoferes() {
        return dashboardService.obtenerTopChoferesConMasIncidencias();
    }
}
