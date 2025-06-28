package com.incidencias.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.incidencias.dto.VehiculoDTO;
import com.incidencias.model.Empresa;
import com.incidencias.model.Vehiculo;
import com.incidencias.repository.EmpresaRepository;
import com.incidencias.repository.VehiculoRepository;

@Service
public class VehiculoService implements IVehiculoService{
    @Autowired
    private VehiculoRepository vehiculoRepository;
    @Autowired
    private EmpresaRepository empresaRepository;

    public List<Vehiculo> listar() {
        return vehiculoRepository.findAll();
    }

    public Optional<Vehiculo> obtenerPorId(Long id) {
        return vehiculoRepository.findById(id);
    }

    public Vehiculo guardar(VehiculoDTO vehiculoDTO) {
        Vehiculo nuevoVehiculo = new Vehiculo();
        Empresa empresa = empresaRepository.findById(vehiculoDTO.getEmpresaId())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        nuevoVehiculo.setEmpresa(
            empresa
        );
        nuevoVehiculo.setPlaca(vehiculoDTO.getPlaca());
        nuevoVehiculo.setMarca(vehiculoDTO.getMarca());
        nuevoVehiculo.setModelo(vehiculoDTO.getModelo());
        nuevoVehiculo.setAnio(vehiculoDTO.getAnio());
        nuevoVehiculo.setTipo(vehiculoDTO.getTipo());
        nuevoVehiculo.setEstado(vehiculoDTO.getEstado());
        Vehiculo guardado = vehiculoRepository.save(nuevoVehiculo);
        FileMirrorUtil.logOperation("vehiculo", "insert", guardado);
        return guardado;
    }

    public Vehiculo actualizar(Long id, Vehiculo vehiculo) {
        return vehiculoRepository.findById(id)
                .map(existingVehiculo -> {
                    existingVehiculo.setPlaca(vehiculo.getPlaca());
                    existingVehiculo.setMarca(vehiculo.getMarca());
                    existingVehiculo.setModelo(vehiculo.getModelo());
                    existingVehiculo.setAnio(vehiculo.getAnio());
                    existingVehiculo.setTipo(vehiculo.getTipo());
                    existingVehiculo.setEstado(vehiculo.getEstado());
                    Vehiculo actualizado = vehiculoRepository.save(existingVehiculo);
                    FileMirrorUtil.logOperation("vehiculo", "update", actualizado);
                    return actualizado;
                })
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));
    }

    public void eliminar(Long id) {
        vehiculoRepository.findById(id).ifPresent(v ->
                FileMirrorUtil.logOperation("vehiculo", "delete", v)
        );
        vehiculoRepository.deleteById(id);
    }
}