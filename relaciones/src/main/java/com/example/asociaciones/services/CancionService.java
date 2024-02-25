package com.example.asociaciones.services;

import com.example.asociaciones.entity.Cancion;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CancionService {


    //Buscar canción
    List<Cancion> findAll();

    //Buscar canciones entre dos fechas
   List<Cancion> findByFechaCreaciónBetween(Date fechaIni, Date fechaFin);

    //Bsucar canción por Id
    Optional<Cancion> findById(Long id);

    //Añadir canción
    Cancion save (Cancion product);

    //Actualizar canción
    Optional <Cancion> update(Long id, Cancion product);

    //Borrar cancion
    Optional<Cancion> delete(Long id);



}
