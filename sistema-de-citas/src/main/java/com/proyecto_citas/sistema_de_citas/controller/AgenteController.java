package com.proyecto_citas.sistema_de_citas.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto_citas.sistema_de_citas.model.Agente;
import com.proyecto_citas.sistema_de_citas.model.Cita;
import com.proyecto_citas.sistema_de_citas.service.AgenteService;
import com.proyecto_citas.sistema_de_citas.service.CitaService;

@RestController
@PreAuthorize("hasRole('AGENTE')")
@RequestMapping("/api/agentes")
public class AgenteController {

    @Autowired
    private CitaService citaService;

    @Autowired
    private AgenteService agenteService;

    @GetMapping("/citas/{agenteId}")
    public ResponseEntity<List<Cita>> obtenerCitasPorAgente(@PathVariable UUID agenteId) {
        Agente agente = agenteService.obtenerAgentePorId(agenteId);
        List<Cita> citas = citaService.obtenerCitasPorAgente(agente);
        return ResponseEntity.ok(citas);
    }

    @PostMapping("/citas/{id}/completar")
    public ResponseEntity<String> completarCita(@PathVariable UUID id) {
        citaService.marcarCitaComoCompletada(id);
        return ResponseEntity.ok("Cita completada con éxito.");
    }
}
