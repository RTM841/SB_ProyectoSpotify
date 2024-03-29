package com.example.asociaciones.services;

import com.example.asociaciones.entity.Cancion;
import com.example.asociaciones.repositories.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CancionServiceImpl implements CancionService{

    @Autowired
    private CancionRepository cancionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Cancion> findAll() {return cancionRepository.findAll();}

    @Override
    public List<Cancion> findByFechaCreacionBetween(Date fechaIni, Date fechaFin) {
        return cancionRepository.findByFechaCreacionBetween(fechaIni, fechaFin);
    }

    @Override
    public List<Cancion> findCancionByPartialNombre(String nombre) {
        // Convertir el nombre a minúsculas para hacer la búsqueda no sensible a mayúsculas/minúsculas
        nombre = nombre.toLowerCase();

        // Realizar la búsqueda en la base de datos
        List<Cancion> canciones = cancionRepository.findByNombreContainingIgnoreCase(nombre);

        return canciones;
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<Cancion> findById(Long id) {return cancionRepository.findById(id);}

    @Override
    @Transactional
    public Cancion save(Cancion cancion) {
        return cancionRepository.save(cancion);
    }


    @Override
    @Transactional
    public Optional<Cancion> update(Long id, Cancion cancion) {
        Optional <Cancion> cancionOptional = cancionRepository.findById(id);
        if(cancionOptional.isPresent()){
            Cancion cancionDb = cancionOptional.orElseThrow();
            cancionDb.setNombre(cancion.getNombre());
            cancionDb.setFechaCreacion(cancion.getFechaCreacion());
            cancionDb.setArtistas(cancion.getArtistas());
            return Optional.of(cancionRepository.save(cancionDb));
        }
        return cancionOptional;
    }

    @Override
    public Optional<Cancion> delete(Long id) {
        Optional <Cancion> cancionOptional = cancionRepository.findById(id);
        cancionOptional.ifPresent( canionDb -> cancionRepository.delete(canionDb));
        return cancionOptional;
    }


    public List<Cancion> findByGeneroNombre(String Nombre) {
        return cancionRepository.findByGeneroNombre(Nombre);
    }

    public List<Cancion> findByFechaCreacion() {
        return cancionRepository.findByFechaCreacion();
    }


}
