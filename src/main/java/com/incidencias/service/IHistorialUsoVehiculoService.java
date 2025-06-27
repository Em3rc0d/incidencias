package com.incidencias.service;

import com.incidencias.model.HistorialUsoVehiculo;

import java.util.List;
import java.util.Optional;

public interface IHistorialUsoVehiculoService {

    List<HistorialUsoVehiculo> listar();

    Optional<HistorialUsoVehiculo> obtenerPorId(Long id);

    HistorialUsoVehiculo guardar(HistorialUsoVehiculo historial);

    void eliminar(Long id);
}
