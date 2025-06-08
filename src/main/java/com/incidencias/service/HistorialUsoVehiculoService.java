package com.incidencias.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.incidencias.model.HistorialUsoVehiculo;
import com.incidencias.repository.HistorialUsoVehiculoRepository;

@Service
public class HistorialUsoVehiculoService {

    private final HistorialUsoVehiculoRepository repository;

    public HistorialUsoVehiculoService(HistorialUsoVehiculoRepository repository) {
        this.repository = repository;
    }

    public List<HistorialUsoVehiculo> listar() {
        return repository.findAll();
    }

    public Optional<HistorialUsoVehiculo> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public HistorialUsoVehiculo guardar(HistorialUsoVehiculo historial) {
        return repository.save(historial);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
