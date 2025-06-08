// EmpresaDAOImpl.java
package com.incidencias.dao;

import com.incidencias.model.Empresa;
import com.incidencias.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmpresaDAOImpl implements EmpresaDAO {

    @Autowired
    private EmpresaRepository repository;

    @Override
    public List<Empresa> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Empresa> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Empresa save(Empresa empresa) {
        return repository.save(empresa);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
