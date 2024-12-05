package com.proyecto_citas.sistema_de_citas.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto_citas.sistema_de_citas.dto.AsignacionRequest;
import com.proyecto_citas.sistema_de_citas.enums.EstadoCita;
import com.proyecto_citas.sistema_de_citas.model.Cita;
import com.proyecto_citas.sistema_de_citas.model.Usuario;
import com.proyecto_citas.sistema_de_citas.service.AdministradorService;
import com.proyecto_citas.sistema_de_citas.service.CitaService;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/administradores")
public class AdministradorController {

    @Autowired
    private CitaService citaService;

    @Autowired
    private AdministradorService administradorService;

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = administradorService.obtenerTodosLosUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/citas/estado/{estado}")
    public ResponseEntity<List<Cita>> obtenerCitasPorEstado(@PathVariable EstadoCita estado) {
        List<Cita> citas = citaService.obtenerCitasPorEstado(estado);
        return ResponseEntity.ok(citas);
    }

    @PostMapping("/citas/{id}/asignar")
    public ResponseEntity<String> asignarCita(@PathVariable UUID id, @RequestBody AsignacionRequest request) {
        citaService.asignarCita(id, request.getAgenteId());
        return ResponseEntity.ok("Cita asignada correctamente.");
    }

    @PostMapping("/citas/{id}/rechazar")
    public ResponseEntity<String> rechazarCita(@PathVariable UUID id) {
        citaService.rechazarCita(id);
        return ResponseEntity.ok("Cita rechazada.");
    }
}
