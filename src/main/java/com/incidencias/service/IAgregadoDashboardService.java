package com.incidencias.service;

import java.util.List;
import com.incidencias.dto.ChoferIncidenciasDTO;
import com.incidencias.dto.EmpresaIncidenciasDTO;
import com.incidencias.dto.IncidenciaAfectadosDTO;

public interface IAgregadoDashboardService {
    List<ChoferIncidenciasDTO> obtenerTopChoferesConMasIncidencias();
    List<IncidenciaAfectadosDTO> obtenerTopIncidenciasConMasAfectados();
    List<EmpresaIncidenciasDTO> obtenerIncidenciasPorEmpresa();

}
