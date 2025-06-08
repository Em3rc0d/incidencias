// AseguradoraDAOImpl.java
package com.incidencias.dao;

import com.incidencias.model.Aseguradora;
import com.incidencias.repository.AseguradoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AseguradoraDAOImpl implements AseguradoraDAO {

    @Autowired
    private AseguradoraRepository repository;

    @Override
    public List<Aseguradora> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Aseguradora> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Aseguradora save(Aseguradora aseguradora) {
        return repository.save(aseguradora);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
