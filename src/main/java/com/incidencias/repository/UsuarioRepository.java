package com.incidencias.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.incidencias.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreo(String correo);
}