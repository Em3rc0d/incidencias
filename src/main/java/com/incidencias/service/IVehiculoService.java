package com.incidencias.service;

import com.incidencias.dto.VehiculoDTO;
import com.incidencias.model.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface IVehiculoService {

    List<Vehiculo> listar();

    Optional<Vehiculo> obtenerPorId(Long id);

    Vehiculo guardar(VehiculoDTO vehiculoDTO);

    Vehiculo actualizar(Long id, Vehiculo vehiculo);

    void eliminar(Long id);
}
