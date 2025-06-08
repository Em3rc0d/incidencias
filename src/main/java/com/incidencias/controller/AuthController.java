package com.incidencias.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incidencias.auth.CustomUserDetailsService;
import com.incidencias.auth.JWTUtil;
import com.incidencias.dto.AuthRequest;
import com.incidencias.dto.UsuarioDTO;
import com.incidencias.model.Empresa;
import com.incidencias.model.Usuario;
import com.incidencias.repository.EmpresaRepository;
import com.incidencias.repository.UsuarioRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;  // inyecta repositorio para guardar

    @Autowired
    private PasswordEncoder passwordEncoder;  // para encriptar contraseñas

    @Autowired
    private EmpresaRepository empresaRepository;

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {
        try {
            authManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getCorreo(), authRequest.getContrasena())
            );
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Credenciales incorrectas");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getCorreo());
        return jwtUtil.generateToken(userDetails.getUsername());
    }

    @PostMapping("/register")
    public String register(@RequestBody UsuarioDTO usuarioDTO) {
        if (usuarioRepository.findByCorreo(usuarioDTO.getCorreo()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }
        Empresa empresa = empresaRepository.findById(usuarioDTO.getEmpresaId())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));  
        Usuario usuario = new Usuario();
        usuario.setEmpresa(empresa);
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setRol(usuarioDTO.getRol());
        // Encripta la contraseña antes de guardar
        usuario.setContrasena(passwordEncoder.encode(usuarioDTO.getContrasena()));

        usuarioRepository.save(usuario);

        // Opcional: retornar token para el usuario recién registrado
        return jwtUtil.generateToken(usuario.getCorreo());
    }
}
