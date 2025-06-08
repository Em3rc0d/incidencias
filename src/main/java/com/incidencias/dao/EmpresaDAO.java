// EmpresaDAO.java
package com.incidencias.dao;

import com.incidencias.model.Empresa;

import java.util.List;
import java.util.Optional;

public interface EmpresaDAO {
    List<Empresa> findAll();
    Optional<Empresa> findById(Long id);
    Empresa save(Empresa empresa);
    void deleteById(Long id);
}
