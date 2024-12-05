package com.proyecto_citas.sistema_de_citas.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto_citas.sistema_de_citas.model.Proyecto;

public interface ProyectoRepository extends JpaRepository<Proyecto, UUID> {
    List<Proyecto> findByActivoTrue();
    Optional<Proyecto> findByNombre(String nombre);
}
