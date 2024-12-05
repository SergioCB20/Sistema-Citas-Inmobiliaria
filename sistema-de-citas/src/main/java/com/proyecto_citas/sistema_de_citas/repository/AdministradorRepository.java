package com.proyecto_citas.sistema_de_citas.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto_citas.sistema_de_citas.model.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, UUID> {
    
}
