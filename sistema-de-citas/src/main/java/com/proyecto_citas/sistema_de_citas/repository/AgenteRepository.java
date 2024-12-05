package com.proyecto_citas.sistema_de_citas.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto_citas.sistema_de_citas.model.Agente;

public interface AgenteRepository extends JpaRepository<Agente, UUID> {
}

