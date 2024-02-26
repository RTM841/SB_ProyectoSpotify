package com.example.asociaciones.services;

import com.example.asociaciones.entity.Cancion;
import com.example.asociaciones.entity.Genero;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface GeneroService {

    //Buscar canción
    List<Genero> findAll();

    //Bsucar canción por Id
    Optional<Genero> findById(Long id);

    //Añadir canción
    Genero save (Genero genero);

    //Actualizar canción
    Optional <Genero> update(Long id, Genero genero);

    //Borrar cancion
    Optional<Genero> delete(Long id);

    //Buscar cuantas canciones tiene un género
    int countByGeneroNombre(String Nombre);
}
