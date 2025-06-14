package com.incidencias.service;

import com.incidencias.dto.ReporteAseguradoraDTO;
import com.incidencias.model.Aseguradora;
import com.incidencias.model.HistorialIncidencia;
import com.incidencias.model.Incidencia;
import com.incidencias.model.ReporteAseguradora;
import com.incidencias.repository.AseguradoraRepository;
import com.incidencias.repository.IncidenciaRepository;
import com.incidencias.repository.ReporteAseguradoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReporteAseguradoraService {

    @Autowired
    private ReporteAseguradoraRepository repository;

    @Autowired
    private IncidenciaRepository incidenciaRepository;

    @Autowired
    private AseguradoraRepository aseguradoraRepository;

    @Autowired
private HistorialIncidenciaService historialService;

@Autowired
private UsuarioService usuarioService; // o como manejes al usuario logueado


    public ReporteAseguradoraService(ReporteAseguradoraRepository repository) {
        this.repository = repository;
    }

    public List<ReporteAseguradora> listar() {
        return repository.findAll();
    }

    public Optional<ReporteAseguradora> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public ReporteAseguradora guardar(ReporteAseguradoraDTO dto) {
        Incidencia incidencia = incidenciaRepository.findById(dto.getIncidencia())
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada"));
        incidencia.setEstado("NOTIFICADO");
        incidenciaRepository.save(incidencia);
        Aseguradora aseguradora = aseguradoraRepository.findById(dto.getAseguradora())
                .orElseThrow(() -> new RuntimeException("Aseguradora no encontrada"));

        ReporteAseguradora reporte = new ReporteAseguradora();
        reporte.setIncidencia(incidencia);
        reporte.setAseguradora(aseguradora);
        reporte.setAsunto(dto.getAsunto());
        reporte.setCuerpo(dto.getCuerpo());
        reporte.setObservaciones(dto.getObservaciones());
        reporte.setFechaEnvio(LocalDateTime.now());
        reporte.setEstadoEnvio("ENVIADO");

        return repository.save(reporte);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public ResponseEntity<ReporteAseguradora> actualizar(Long id, ReporteAseguradora actualizado) {
    return repository.findById(id).map(existing -> {
        actualizado.setId(id);
        ReporteAseguradora guardado = repository.save(actualizado);

        String estado = actualizado.getEstadoEnvio();
        if ("CERRADA".equalsIgnoreCase(estado) || "ANULADA".equalsIgnoreCase(estado)) {
            Incidencia incidencia = incidenciaRepository.findById(guardado.getIncidencia().getId())
                    .orElseThrow(() -> new RuntimeException("Incidencia no encontrada"));
            incidencia.setEstado(estado);
            incidenciaRepository.save(incidencia);
            HistorialIncidencia historial = new HistorialIncidencia();
            historial.setEstado(estado);
            historial.setObservacion(actualizado.getObservaciones());
            historial.setFecha(LocalDateTime.now());

            historial.setIncidencia(guardado.getIncidencia());

            historial.setUsuario(guardado.getIncidencia().getUsuario());

            historialService.guardar(historial);
        }

        return ResponseEntity.ok(guardado);
    }).orElse(ResponseEntity.notFound().build());
}

}
