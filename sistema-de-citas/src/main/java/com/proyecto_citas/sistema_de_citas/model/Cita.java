package com.proyecto_citas.sistema_de_citas.model;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proyecto_citas.sistema_de_citas.enums.EstadoCita;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
@Entity
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private LocalDateTime fecha;

    @NotNull
    @ManyToOne
    private Proyecto proyecto;

    @NotBlank
    @Size(max = 255)
    private String consulta;

    @NotNull
    @ManyToOne
    @JsonIgnore
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    @NotNull
    private EstadoCita estadoCita;

    private LocalDateTime fechaDeAsignacion;
    private LocalDateTime fechaDeCerrado;

    @ManyToOne
    private Agente agenteAsignado;
    
}

