package com.proyecto_citas.sistema_de_citas.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto_citas.sistema_de_citas.dto.CitaRequest;
import com.proyecto_citas.sistema_de_citas.dto.ReaperturaRequest;
import com.proyecto_citas.sistema_de_citas.model.Cita;
import com.proyecto_citas.sistema_de_citas.model.Cliente;
import com.proyecto_citas.sistema_de_citas.service.CitaService;
import com.proyecto_citas.sistema_de_citas.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@PreAuthorize("hasRole('CLIENTE')")
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private CitaService citaService;

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/citas")
    public ResponseEntity<Cita> registrarCita(@RequestBody @Valid CitaRequest request) {
        // Obtener el usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = authentication.getName();

        // Delegar la creación de la cita al servicio
        Cita cita = citaService.crearCita(request, nombreUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(cita);
    }

    @GetMapping("/citas/{clienteId}")
    public ResponseEntity<List<Cita>> obtenerCitasPorCliente(@PathVariable UUID clienteId) {
        Cliente cliente = clienteService.obtenerClientePorId(clienteId);
        List<Cita> citas = citaService.obtenerCitasPorCliente(cliente);
        return ResponseEntity.ok(citas);
    }

    @PostMapping("/citas/{id}/reabrir")
    public ResponseEntity<String> solicitarReapertura(@PathVariable UUID id, @RequestBody ReaperturaRequest request) {
        citaService.solicitarReapertura(id, request.getMotivo());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Solicitud de reapertura enviada.");
    }
}
