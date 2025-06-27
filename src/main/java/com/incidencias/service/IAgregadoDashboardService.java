package com.incidencias.service;

import java.util.List;
import com.incidencias.dto.ChoferIncidenciasDTO;

public interface IAgregadoDashboardService {
    List<ChoferIncidenciasDTO> obtenerTopChoferesConMasIncidencias();
}
