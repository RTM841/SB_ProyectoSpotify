package com.example.asociaciones.services;

import com.example.asociaciones.entity.Cancion;
import com.example.asociaciones.entity.Usuario;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> findAll();

    Usuario save(Usuario usuario);

    UserDetails loadUserByUsername(String username);

    //Actualizar Usuario
    Optional<Usuario> update(Long id, Usuario usuario);

    //Borrar cancion
    Optional<Usuario> delete(Long id);

}

