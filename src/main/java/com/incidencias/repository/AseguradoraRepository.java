// AseguradoraRepository.java
package com.incidencias.repository;

import com.incidencias.model.Aseguradora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AseguradoraRepository extends JpaRepository<Aseguradora, Long> {
}
