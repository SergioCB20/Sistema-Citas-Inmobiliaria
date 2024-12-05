package com.proyecto_citas.sistema_de_citas.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto_citas.sistema_de_citas.enums.RolUsuario;
import com.proyecto_citas.sistema_de_citas.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByNombre(String nombre);

    List<Usuario> findByRol(RolUsuario rol);
}

