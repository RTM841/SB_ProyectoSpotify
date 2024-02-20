package com.example.asociaciones.services;

import com.example.asociaciones.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    List<Usuario> findAll();

    Usuario save(Usuario usuario);

}

