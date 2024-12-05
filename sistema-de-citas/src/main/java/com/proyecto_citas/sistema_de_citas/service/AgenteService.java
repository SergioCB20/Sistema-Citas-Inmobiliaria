package com.proyecto_citas.sistema_de_citas.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_citas.sistema_de_citas.exception.ResourceNotFoundException;
import com.proyecto_citas.sistema_de_citas.model.Agente;
import com.proyecto_citas.sistema_de_citas.repository.AgenteRepository;

@Service
public class AgenteService {

    @Autowired
    private AgenteRepository agenteRepository;

    public Agente obtenerAgentePorId(UUID agenteId) {
        return agenteRepository.findById(agenteId)
                .orElseThrow(() -> new ResourceNotFoundException("Agente no encontrado con ID: " + agenteId));
    }
}

