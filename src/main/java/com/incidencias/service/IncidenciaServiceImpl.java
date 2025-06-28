package com.incidencias.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.incidencias.dto.IncidenciaDTO;
import com.incidencias.model.Incidencia;
import com.incidencias.model.TipoIncidencia;
import com.incidencias.model.Usuario;
import com.incidencias.model.Vehiculo;
import com.incidencias.repository.IncidenciaRepository;
import com.incidencias.repository.TipoIncidenciaRepository;
import com.incidencias.repository.UsuarioRepository;
import com.incidencias.repository.VehiculoRepository;
import com.incidencias.service.IncidenciaService;
import org.springframework.data.domain.Sort;

@Service
public class IncidenciaServiceImpl implements IncidenciaService {

    @Autowired
    private IncidenciaRepository incidenciaRepo;

    @Autowired
    private VehiculoRepository vehiculoRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private TipoIncidenciaRepository tipoIncidenciaRepo;

    @Override
    public Incidencia crear(IncidenciaDTO dto) {
        Incidencia incidencia = mapToEntity(dto);
        return incidenciaRepo.save(incidencia);
    }

    @Override
    public IncidenciaDTO actualizar(Long id, IncidenciaDTO dto) {
        Incidencia incidencia = incidenciaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada"));
        incidencia.setDescripcion(dto.getDescripcion());
        incidencia.setPrioridad(dto.getPrioridad());
        incidencia.setEstado(dto.getEstado());
        incidencia.setFechaReporte(dto.getFechaReporte());
        incidencia.setLatitud(dto.getLatitud());
        incidencia.setLongitud(dto.getLongitud());
        incidencia.setVehiculo(vehiculoRepo.findById(dto.getVehiculoId()).orElseThrow());
        incidencia.setUsuario(usuarioRepo.findById(dto.getUsuarioId()).orElseThrow());
        incidencia.setTipoIncidencia(tipoIncidenciaRepo.findById(dto.getTipoIncidenciaId()).orElseThrow());
        return mapToDTO(incidenciaRepo.save(incidencia));
    }

    @Override
    public Incidencia obtenerPorId(Long id) {
        return incidenciaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada"));
    }

    @Override
    public List<Incidencia> listarTodos() {
        return incidenciaRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public void eliminar(Long id) {
        incidenciaRepo.deleteById(id);
    }

    @Override
    public List<Incidencia> obtenerPorEmpresaId(Long empresaId) {
        return incidenciaRepo.findByEmpresaId(empresaId);
    }

    @Override
    public List<Incidencia> obtenerPorUsuarioId(Long userId) {
        return incidenciaRepo.findByUsuarioId(userId);
    }

    private Incidencia mapToEntity(IncidenciaDTO dto) {
        Incidencia i = new Incidencia();
        i.setDescripcion(dto.getDescripcion());
        i.setPrioridad(dto.getPrioridad());
        i.setEstado(dto.getEstado());
        i.setFechaReporte(dto.getFechaReporte());
        i.setLatitud(dto.getLatitud());
        i.setLongitud(dto.getLongitud());
        i.setVehiculo(vehiculoRepo.findById(dto.getVehiculoId()).orElseThrow());
        i.setUsuario(usuarioRepo.findById(dto.getUsuarioId()).orElseThrow());
        i.setTipoIncidencia(tipoIncidenciaRepo.findById(dto.getTipoIncidenciaId()).orElseThrow());
        return i;
    }

    private IncidenciaDTO mapToDTO(Incidencia i) {
        IncidenciaDTO dto = new IncidenciaDTO();
        dto.setDescripcion(i.getDescripcion());
        dto.setPrioridad(i.getPrioridad());
        dto.setEstado(i.getEstado());
        dto.setFechaReporte(i.getFechaReporte());
        dto.setLatitud(i.getLatitud());
        dto.setLongitud(i.getLongitud());
        dto.setVehiculoId(i.getVehiculo().getId());
        dto.setUsuarioId(i.getUsuario().getId());
        dto.setTipoIncidenciaId(i.getTipoIncidencia().getId());
        return dto;
    }

    @Override
    public List<Incidencia> obtenerIncidenciasAntiguasNoResueltas(Long empresaId) {
        LocalDateTime hace7dias = LocalDateTime.now().minusDays(7);
        return incidenciaRepo.findIncidenciasNoResueltasMas7Dias(hace7dias, empresaId);
    }
}
