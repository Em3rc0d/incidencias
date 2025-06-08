// AseguradoraDAO.java
package com.incidencias.dao;

import com.incidencias.model.Aseguradora;
import java.util.List;
import java.util.Optional;

public interface AseguradoraDAO {
    List<Aseguradora> findAll();
    Optional<Aseguradora> findById(Long id);
    Aseguradora save(Aseguradora aseguradora);
    void deleteById(Long id);
}
