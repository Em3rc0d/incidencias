package com.incidencias.service;

import com.incidencias.dto.UsuarioDTO;
import com.incidencias.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    UsuarioDTO crearUsuario(UsuarioDTO dto);

    UsuarioDTO obtenerUsuarioPorId(Long id);

    List<Usuario> listarUsuarios();

    Usuario actualizarUsuario(Long id, UsuarioDTO dto);

    void eliminarUsuario(Long id);

    List<Usuario> obtenerUsuariosPorEmpresaId(Long empresaId);

    Optional<Usuario> obtenerUsuarioPorEmail(String email);
}
