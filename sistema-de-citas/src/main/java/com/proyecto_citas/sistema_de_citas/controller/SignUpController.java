package com.proyecto_citas.sistema_de_citas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto_citas.sistema_de_citas.dto.SignUpRequest;
import com.proyecto_citas.sistema_de_citas.service.SignUpService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/signup")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @PostMapping
    public ResponseEntity<String> registrarUsuario(@RequestBody @Valid SignUpRequest signUpRequest) {
        signUpService.registrarUsuario(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado correctamente");
    }
}

