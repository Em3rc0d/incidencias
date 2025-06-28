package com.incidencias.service;

import java.util.List;
import com.incidencias.dto.ChoferIncidenciasDTO;
import com.incidencias.dto.EmpresaIncidenciasDTO;
import com.incidencias.dto.IncidenciaAfectadosDTO;
import com.incidencias.model.Incidencia;

public interface IAgregadoDashboardService {
    List<ChoferIncidenciasDTO> obtenerTopChoferesConMasIncidencias(Long empresaId);
    List<IncidenciaAfectadosDTO> obtenerTopIncidenciasConMasAfectados(Long empresaId);
    List<EmpresaIncidenciasDTO> obtenerIncidenciasPorEmpresa(Long empresaId);
}
