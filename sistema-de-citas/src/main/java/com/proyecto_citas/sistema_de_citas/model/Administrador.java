package com.proyecto_citas.sistema_de_citas.model;
import com.proyecto_citas.sistema_de_citas.enums.RolUsuario;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
@Entity
@NoArgsConstructor
public class Administrador extends Usuario {
    public Administrador(String nombre, String password) {
        super(nombre,password,RolUsuario.ROLE_ADMIN);
    }
}

