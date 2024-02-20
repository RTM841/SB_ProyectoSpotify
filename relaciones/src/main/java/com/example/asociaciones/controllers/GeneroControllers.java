package com.example.asociaciones.controllers;


import com.example.asociaciones.entity.Cancion;
import com.example.asociaciones.entity.Genero;
import com.example.asociaciones.services.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/generos")
public class GeneroControllers {

    @Autowired
    private GeneroService generoService;

    @GetMapping
    public List<Genero> list(){return generoService.findAll();}
    @GetMapping("/{id}")
    public ResponseEntity<Genero> view(@PathVariable Long id){
        Optional<Genero> productOptional = generoService.findById(id);
        if(productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Genero> create(@RequestBody Genero genero){
        return ResponseEntity.status(HttpStatus.CREATED).body(generoService.save(genero));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genero> update(@PathVariable Long id, @RequestBody Genero genero){
        Optional <Genero> productOptional = generoService.update(id, genero);
        if(productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Genero> delete(@PathVariable Long id){
        Optional<Genero> productOptional = generoService.delete(id);
        if(productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
