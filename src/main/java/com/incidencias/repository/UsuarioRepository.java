package com.incidencias.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.incidencias.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreo(String correo);

    @Query("SELECT u FROM Usuario u WHERE u.empresa.id = :empresaId")
    List<Usuario> findByEmpresaId(Long empresaId);
}