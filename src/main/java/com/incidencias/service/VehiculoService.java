package com.incidencias.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incidencias.model.Vehiculo;
import com.incidencias.repository.VehiculoRepository;

@Service
public class VehiculoService {
    @Autowired
    private VehiculoRepository vehiculoRepository;

    public List<Vehiculo> listar() {
        return vehiculoRepository.findAll();
    }

    public Optional<Vehiculo> obtenerPorId(Long id) {
        return vehiculoRepository.findById(id);
    }

    public Vehiculo guardar(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    public void eliminar(Long id) {
        vehiculoRepository.deleteById(id);
    }
}