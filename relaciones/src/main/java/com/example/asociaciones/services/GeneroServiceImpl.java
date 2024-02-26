package com.example.asociaciones.services;

import com.example.asociaciones.entity.Cancion;
import com.example.asociaciones.entity.Genero;
import com.example.asociaciones.repositories.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class GeneroServiceImpl implements GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Genero> findAll() {
        return  generoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Genero> findById(Long id) {
        return generoRepository.findById(id);
    }

    @Override
    @Transactional
    public Genero save(Genero genero) {
        return generoRepository.save(genero);
    }

    @Override
    @Transactional
    public Optional <Genero> update(Long id, Genero genero) {
        Optional <Genero> generoOptional = generoRepository.findById(id);
        if(generoOptional.isPresent()){
            Genero generoDb = generoOptional.orElseThrow();
            generoDb.setNombre(genero.getNombre());
            return Optional.of(generoRepository.save(generoDb));
        }
        return generoOptional;
    }

    @Override
    @Transactional
    public Optional<Genero> delete(Long id) {
        Optional <Genero> productOptional = generoRepository.findById(id);
        productOptional.ifPresent( productDb -> generoRepository.delete(productDb));
        return productOptional;
    }

    @Override
    public int countByGeneroNombre(String Nombre) {
        return generoRepository.countByGeneroNombre(Nombre);
    }

}
