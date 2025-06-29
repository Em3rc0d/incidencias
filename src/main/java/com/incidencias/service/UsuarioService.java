package com.incidencias.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incidencias.dto.UsuarioDTO;
import com.incidencias.model.Empresa;
import com.incidencias.model.Usuario;
import com.incidencias.repository.EmpresaRepository;
import com.incidencias.repository.UsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    private UsuarioDTO mapToDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setEmpresaId(usuario.getEmpresa().getId());
        dto.setNombre(usuario.getNombre());
        dto.setCorreo(usuario.getCorreo());
        dto.setContrasena(usuario.getContrasena());
        dto.setRol(usuario.getRol());
        return dto;
    }

    private Usuario mapToEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        Empresa empresa = empresaRepository.findById(dto.getEmpresaId())
                            .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        usuario.setEmpresa(empresa);
        usuario.setNombre(dto.getNombre());
        usuario.setCorreo(dto.getCorreo());
        usuario.setContrasena(dto.getContrasena());
        usuario.setRol(dto.getRol());
        return usuario;
    }

    public UsuarioDTO crearUsuario(UsuarioDTO dto) {
        Usuario usuario = mapToEntity(dto);
        Usuario guardado = usuarioRepository.save(usuario);
        FileMirrorUtil.logOperation("usuario", "insert", guardado);
        return mapToDTO(guardado);
    }

    public UsuarioDTO obtenerUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return mapToDTO(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario actualizarUsuario(Long id, UsuarioDTO dto) {
        if (id == null) {
            throw new IllegalArgumentException("El ID del usuario no puede ser null.");
        }

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        usuario.setNombre(dto.getNombre());
        usuario.setCorreo(dto.getCorreo());
        usuario.setContrasena(usuario.getContrasena());
        usuario.setRol(dto.getRol());

        Empresa empresa = empresaRepository.findById(dto.getEmpresaId())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con ID: " + dto.getEmpresaId()));

        usuario.setEmpresa(empresa);

        Usuario actualizado = usuarioRepository.save(usuario);
        FileMirrorUtil.logOperation("usuario", "update", actualizado);
        return actualizado;
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.findById(id).ifPresent(u ->
                FileMirrorUtil.logOperation("usuario", "delete", u)
        );
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> obtenerUsuariosPorEmpresaId(Long empresaId) {
        if (empresaId == null) {
            throw new IllegalArgumentException("El ID de la empresa no puede ser null.");
        }
        return usuarioRepository.findByEmpresaId(empresaId);
    }

    public Optional<Usuario> obtenerUsuarioPorEmail(String email) {
        if( email == null || email.isEmpty()) {
            throw new IllegalArgumentException("El correo electrónico no puede ser null o vacío.");
        }
        return usuarioRepository.findByCorreo(email);
    }
}
