package com.proyecto_citas.sistema_de_citas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.proyecto_citas.sistema_de_citas.model.Usuario;
import com.proyecto_citas.sistema_de_citas.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public CustomUserDetailsService() {
        System.out.println("CustomUserDetailsService inicializado");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Entra a load con username: " + username);

        Optional<Usuario> optionalUsuario = usuarioRepository.findByNombre(username);
        System.out.println("Optional contiene usuario: " + optionalUsuario.isPresent());

        Usuario usuario = optionalUsuario.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        if (usuario.getNombre() == null || usuario.getPassword() == null || usuario.getRol() == null) {
            throw new IllegalStateException("El usuario tiene datos incompletos: " + usuario);
        }

        System.out.println("Usuario encontrado: " + usuario.getNombre() + ", rol: " + usuario.getRol().name());

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(usuario.getNombre())
                .password(usuario.getPassword())
                .authorities(usuario.getRol().name())
                .build();

        System.out.println("UserDetails construido: " + userDetails);
        return userDetails;
    }

}
