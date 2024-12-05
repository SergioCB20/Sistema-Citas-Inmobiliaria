package com.proyecto_citas.sistema_de_citas.dto;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public class AsignacionRequest {
    @NotNull
    private UUID agenteId;
    public UUID getAgenteId(){
        return this.agenteId;
    }
    public void setAgenteId(UUID id){
        this.agenteId = id;
    }
}

