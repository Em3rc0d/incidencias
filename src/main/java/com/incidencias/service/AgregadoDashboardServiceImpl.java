package com.incidencias.service;

import com.incidencias.dto.ChoferIncidenciasDTO;
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
    public List<ChoferIncidenciasDTO> obtenerTopChoferesConMasIncidencias() {
        return incidenciaRepository.topChoferesConMasIncidencias()
            .stream()
            .map(obj -> new ChoferIncidenciasDTO((String) obj[0], ((Number) obj[1]).longValue()))
            .collect(Collectors.toList());
    }
}
