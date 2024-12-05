package com.proyecto_citas.sistema_de_citas.model;

import java.util.UUID;

import com.proyecto_citas.sistema_de_citas.enums.RolUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    @Size(max = 100)
    @Column(unique = true)
    private String nombre;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RolUsuario rol;

    public Usuario(String nombre, String password, RolUsuario rol) {
        this.nombre = nombre;
        this.password = password;
        this.rol = rol;
    }
    
}

