package com.proyecto_citas.sistema_de_citas.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_citas.sistema_de_citas.exception.ResourceNotFoundException;
import com.proyecto_citas.sistema_de_citas.model.Cliente;
import com.proyecto_citas.sistema_de_citas.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente obtenerClientePorId(UUID clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + clienteId));
    }
}

