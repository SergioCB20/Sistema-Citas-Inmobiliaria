package com.proyecto_citas.sistema_de_citas.model;
import java.util.ArrayList;
import java.util.List;

import com.proyecto_citas.sistema_de_citas.enums.RolUsuario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.NoArgsConstructor;
@Entity
@NoArgsConstructor
public class Agente extends Usuario {

    @OneToMany(mappedBy = "agenteAsignado", cascade = CascadeType.ALL)
    private List<Cita> citas = new ArrayList<>();

    public Agente(String nombre, String password) {
        super(nombre,password,RolUsuario.ROLE_AGENTE);
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
}

