package com.example.asociaciones.services;

import com.example.asociaciones.entity.Cancion;

import java.util.List;
import java.util.Optional;

public interface CancionService {

    List<Cancion> findAll();
    Optional<Cancion> findById(Long id);
    Cancion save (Cancion product);

    Optional <Cancion> update(Long id, Cancion product);
    Optional<Cancion> delete(Long id);

}
