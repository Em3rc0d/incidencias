package com.incidencias.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.incidencias.dto.IncidenciaDTO;
import com.incidencias.model.Incidencia;
import com.incidencias.repository.IncidenciaRepository;
import com.incidencias.repository.TipoIncidenciaRepository;
import com.incidencias.repository.UsuarioRepository;
import com.incidencias.repository.VehiculoRepository;

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

    @Autowired
    private WarehouseService warehouseService;

    @Override
    public Incidencia crear(IncidenciaDTO dto) {
        Incidencia incidencia = mapToEntity(dto);
        Incidencia guardada = incidenciaRepo.save(incidencia);

        FileMirrorUtil.logOperation("incidencia", "insert", guardada);
        Map<String, Object> wh = toWarehouseMap(guardada);
        warehouseService.saveOrUpdate("view_incidencia_warehouse", wh);

        return guardada;
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

        Incidencia actualizada = incidenciaRepo.save(incidencia);
        FileMirrorUtil.logOperation("incidencia", "update", actualizada);

        Map<String, Object> wh = toWarehouseMap(actualizada);
        warehouseService.saveOrUpdate("view_incidencia_warehouse", wh);

        return mapToDTO(actualizada);
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
        incidenciaRepo.findById(id).ifPresent(i -> FileMirrorUtil.logOperation("incidencia", "delete", i));
        incidenciaRepo.deleteById(id);
        warehouseService.delete("view_incidencia_warehouse", id);

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
    public List<Incidencia> obtenerIncidenciasAntiguasNoResueltas() {
        LocalDateTime hace7dias = LocalDateTime.now().minusDays(7);
        return incidenciaRepo.findIncidenciasNoResueltasMas7Dias(hace7dias);
    }

    private Map<String, Object> toWarehouseMap(Incidencia incidencia) {
        Map<String, Object> wh = new HashMap<>();
//        wh.put("id", incidencia.getId());
//        wh.put("id_vehiculo", incidencia.getVehiculo().getId());
//        wh.put("id_usuario", incidencia.getUsuario().getId());
//        wh.put("id_tipo_incidencia", incidencia.getTipoIncidencia().getId());
//        wh.put("fecha_reporte", incidencia.getFechaReporte());
        wh.put("id", incidencia.getId());
        wh.put("vehiculo", incidencia.getVehiculo().getPlaca());
        wh.put("usuario", incidencia.getUsuario().getNombre());
        wh.put("tipo_incidencia", incidencia.getTipoIncidencia().getNombre());
        return wh;
    }

    // Select * from id join join join join

}
