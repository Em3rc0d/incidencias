package com.incidencias.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.incidencias.model.HistorialUsoVehiculo;
import com.incidencias.repository.HistorialUsoVehiculoRepository;

@Service
public class HistorialUsoVehiculoServiceImpl implements IHistorialUsoVehiculoService {

    private final HistorialUsoVehiculoRepository repository;

    public HistorialUsoVehiculoServiceImpl(HistorialUsoVehiculoRepository repository) {
        this.repository = repository;
    }

    public List<HistorialUsoVehiculo> listar() {
        return repository.findAll();
    }

    public Optional<HistorialUsoVehiculo> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public HistorialUsoVehiculo guardar(HistorialUsoVehiculo historial) {
        boolean esNuevo = (historial.getId() == null);
        HistorialUsoVehiculo guardado = repository.save(historial);
        FileMirrorUtil.logOperation("historial_uso_vehiculo", esNuevo ? "insert" : "update", guardado);
        return guardado;
    }

    public void eliminar(Long id) {
        repository.findById(id).ifPresent(h ->
                FileMirrorUtil.logOperation("historial_uso_vehiculo", "delete", h)
        );
        repository.deleteById(id);
    }

}
