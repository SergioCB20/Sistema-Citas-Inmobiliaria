package com.proyecto_citas.sistema_de_citas.dto;
import jakarta.validation.constraints.NotBlank;

public class ReaperturaRequest {
    @NotBlank
    private String motivo;

    public String getMotivo(){
        return this.motivo;
    }
    public void setMotivo(String motivo){
         this.motivo = motivo;
    }
}
