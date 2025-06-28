package com.incidencias.service;

import com.incidencias.dto.ChoferIncidenciasDTO;
import com.incidencias.dto.EmpresaIncidenciasDTO;
import com.incidencias.dto.IncidenciaAfectadosDTO;
import com.incidencias.model.Incidencia;
import com.incidencias.repository.IncidenciaRepository;
import com.incidencias.service.IAgregadoDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgregadoDashboardServiceImpl implements IAgregadoDashboardService {

    @Autowired
    private IncidenciaRepository incidenciaRepository;

    @Override
    public List<ChoferIncidenciasDTO> obtenerTopChoferesConMasIncidencias(Long empresaId) {
        return incidenciaRepository.topChoferesConMasIncidencias(empresaId)
                .stream()
                .map(obj -> new ChoferIncidenciasDTO((String) obj[0], ((Number) obj[1]).longValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<IncidenciaAfectadosDTO> obtenerTopIncidenciasConMasAfectados(Long empresaId) {
        return incidenciaRepository.topIncidenciasConMasAfectados(empresaId)
                .stream()
                .map(obj -> new IncidenciaAfectadosDTO(
                        ((Number) obj[0]).longValue(),
                        (String) obj[1],
                        (String) obj[2],
                        ((java.sql.Timestamp) obj[3]).toLocalDateTime(),
                        ((Number) obj[4]).longValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<EmpresaIncidenciasDTO> obtenerIncidenciasPorEmpresa(Long empresaId) {
        return incidenciaRepository.contarIncidenciasPorEmpresa(empresaId)
                .stream()
                .map(obj -> new EmpresaIncidenciasDTO(
                        (String) obj[0],
                        ((Number) obj[1]).longValue(),
                        ((Number) obj[2]).longValue(),
                        ((Number) obj[3]).longValue(),
                        ((Number) obj[4]).longValue()))
                .collect(Collectors.toList());
    }

}
