package com.proyecto_citas.sistema_de_citas.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_citas.sistema_de_citas.exception.ResourceNotFoundException;
import com.proyecto_citas.sistema_de_citas.model.Administrador;
import com.proyecto_citas.sistema_de_citas.model.Usuario;
import com.proyecto_citas.sistema_de_citas.repository.AdministradorRepository;
import com.proyecto_citas.sistema_de_citas.repository.UsuarioRepository;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Administrador obtenerAdministradorPorId(UUID adminId) {
        return administradorRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador no encontrado con ID: " + adminId));
    }
}
