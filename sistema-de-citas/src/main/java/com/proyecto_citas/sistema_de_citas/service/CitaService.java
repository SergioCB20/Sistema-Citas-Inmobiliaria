package com.proyecto_citas.sistema_de_citas.service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_citas.sistema_de_citas.dto.CitaRequest;
import com.proyecto_citas.sistema_de_citas.enums.EstadoCita;
import com.proyecto_citas.sistema_de_citas.exception.ResourceNotFoundException;
import com.proyecto_citas.sistema_de_citas.model.Agente;
import com.proyecto_citas.sistema_de_citas.model.Cita;
import com.proyecto_citas.sistema_de_citas.model.Cliente;
import com.proyecto_citas.sistema_de_citas.model.Proyecto;
import com.proyecto_citas.sistema_de_citas.repository.AgenteRepository;
import com.proyecto_citas.sistema_de_citas.repository.CitaRepository;
import com.proyecto_citas.sistema_de_citas.repository.ClienteRepository;
import com.proyecto_citas.sistema_de_citas.repository.ProyectoRepository;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AgenteRepository agenteRepository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    public Cita crearCita(CitaRequest request, String nombreUsuario) {
        // Buscar al cliente por el nombre de usuario autenticado
        Cliente cliente = clienteRepository.findByNombre(nombreUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
    
        // Buscar el proyecto por nombre
        Proyecto proyecto = proyectoRepository.findByNombre(request.getProyecto())
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado"));
    
        // Validar que el proyecto esté activo
        if (!proyecto.isActivo()) {
            throw new IllegalStateException("El proyecto no está activo");
        }
    
        // Crear la cita
        Cita cita = new Cita();
        cita.setCliente(cliente);
        cita.setProyecto(proyecto);
        cita.setConsulta(request.getConsulta());
        cita.setFecha(request.getFecha());
        cita.setEstadoCita(EstadoCita.ENVIADO);
    
        // Guardar la cita en la base de datos
        return citaRepository.save(cita);
    }
    

    public List<Cita> obtenerCitasPorCliente(Cliente cliente) {
        return citaRepository.findByCliente(cliente);
    }

    public void asignarCita(UUID citaId, UUID agenteId) {
        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada"));

        if (cita.getEstadoCita() != EstadoCita.ENVIADO) {
            throw new IllegalStateException("La cita no está en estado ENVIADO");
        }

        Agente agente = agenteRepository.findById(agenteId)
                .orElseThrow(() -> new ResourceNotFoundException("Agente no encontrado"));

        cita.setEstadoCita(EstadoCita.ASIGNADO);
        cita.setAgenteAsignado(agente);
        cita.setFechaDeAsignacion(LocalDateTime.now());

        citaRepository.save(cita);
    }

    public void marcarCitaComoCompletada(UUID citaId) {
        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada"));

        if (cita.getEstadoCita() != EstadoCita.ASIGNADO) {
            throw new IllegalStateException("Solo se pueden completar citas asignadas");
        }

        cita.setEstadoCita(EstadoCita.COMPLETADO);
        cita.setFechaDeCerrado(LocalDateTime.now());

        citaRepository.save(cita);
    }

    public void rechazarCita(UUID citaId) {
        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada"));

        if (cita.getEstadoCita() != EstadoCita.ENVIADO) {
            throw new IllegalStateException("Solo se pueden rechazar citas en estado ENVIADO");
        }

        cita.setEstadoCita(EstadoCita.RECHAZADO);

        citaRepository.save(cita);
    }

    public void solicitarReapertura(UUID citaId, String motivo) {
        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada"));

        if (cita.getEstadoCita() != EstadoCita.COMPLETADO) {
            throw new IllegalStateException("Solo se pueden reabrir citas completadas");
        }

        cita.setEstadoCita(EstadoCita.REABIERTO);
        citaRepository.save(cita);
    }

    public List<Cita> obtenerCitasPorAgente(Agente agente) {
        return citaRepository.findByAgenteAsignado(agente);
    }

    public List<Cita> obtenerCitasPorEstado(EstadoCita estado) {
        return citaRepository.findByEstadoCita(estado);
    }
}

