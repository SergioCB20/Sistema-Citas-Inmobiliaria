package com.proyecto_citas.sistema_de_citas.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto_citas.sistema_de_citas.model.Cliente;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    Optional<Cliente> findByNombre(String nombre);
}

