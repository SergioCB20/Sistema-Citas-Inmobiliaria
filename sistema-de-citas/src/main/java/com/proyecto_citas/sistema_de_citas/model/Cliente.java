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
public class Cliente extends Usuario {

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cita> citas = new ArrayList<>();

    public Cliente(String nombre,String password) {
        super(nombre,password,RolUsuario.ROLE_CLIENTE);
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }
}

