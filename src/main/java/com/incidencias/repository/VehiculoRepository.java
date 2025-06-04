package com.incidencias.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incidencias.model.Vehiculo;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    Optional<Vehiculo> findByPlaca(String placa);
}
