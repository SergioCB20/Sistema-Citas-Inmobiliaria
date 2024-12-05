package com.proyecto_citas.sistema_de_citas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto_citas.sistema_de_citas.dto.SignUpRequest;
import com.proyecto_citas.sistema_de_citas.model.Administrador;
import com.proyecto_citas.sistema_de_citas.model.Agente;
import com.proyecto_citas.sistema_de_citas.model.Cliente;
import com.proyecto_citas.sistema_de_citas.repository.AdministradorRepository;
import com.proyecto_citas.sistema_de_citas.repository.AgenteRepository;
import com.proyecto_citas.sistema_de_citas.repository.ClienteRepository;

@Service
public class SignUpService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AgenteRepository agenteRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registrarUsuario(SignUpRequest signUpRequest) {
        String nombre = signUpRequest.getNombre();
        String password = passwordEncoder.encode(signUpRequest.getPassword());
        String tipoUsuario = signUpRequest.getTipoUsuario();

        switch (tipoUsuario.toUpperCase()) {
            case "CLIENTE" -> {
                Cliente cliente = new Cliente(nombre, password);
                System.out.println("Registrando cliente: " + cliente);
                clienteRepository.save(cliente);
            }
            case "AGENTE" -> {
                Agente agente = new Agente(nombre, password);
                agenteRepository.save(agente);
            }
            case "ADMINISTRADOR" -> {
                Administrador administrador = new Administrador(nombre, password);
                administradorRepository.save(administrador);
            }
            default -> throw new IllegalArgumentException("Tipo de usuario no válido: " + tipoUsuario);
        }
    }
}

