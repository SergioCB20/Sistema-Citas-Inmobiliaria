package com.proyecto_citas.sistema_de_citas.dto;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class CitaRequest {;

    @NotBlank
    private String proyecto;

    @NotBlank
    private String consulta;

    @NotNull
    private LocalDateTime fecha;

}

