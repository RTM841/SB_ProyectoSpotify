package com.example.asociaciones.controllers;

import com.example.asociaciones.entity.Cancion;
import com.example.asociaciones.services.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/canciones")
public class CancionControllers {

        @Autowired
        private CancionService cancionService;

        @GetMapping
        public List<Cancion> list(){
            return cancionService.findAll();
        }
        @GetMapping("/{id}")
        public ResponseEntity<Cancion> view(@PathVariable Long id){
            Optional<Cancion> productOptional = cancionService.findById(id);
            if(productOptional.isPresent()){
                return ResponseEntity.ok(productOptional.orElseThrow());
            }
            return ResponseEntity.notFound().build();
        }

        @PostMapping
        public ResponseEntity<Cancion> create(@RequestBody Cancion cancion){
            return ResponseEntity.status(HttpStatus.CREATED).body(cancionService.save(cancion));
        }

        @PutMapping("/{id}")
        public ResponseEntity<Cancion> update(@PathVariable Long id, @RequestBody Cancion product){
            Optional <Cancion> productOptional = cancionService.update(id, product);
            if(productOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
            }
            return ResponseEntity.notFound().build();
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Cancion> delete(@PathVariable Long id){
            Optional<Cancion> productOptional = cancionService.delete(id);
            if(productOptional.isPresent()){
                return ResponseEntity.ok(productOptional.orElseThrow());
            }
            return ResponseEntity.notFound().build();
        }

    @GetMapping("/betweenDates")
    public ResponseEntity<?> getUsersBetweenDates(@RequestParam("fechaIni") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaIni,
                                                  @RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
        List<Cancion> usuarios = cancionService.findByFechaCreaciónBetween(fechaIni, fechaFin);

        // Aquí puedes devolver la lista de usuarios encontrados como respuesta
        return ResponseEntity.ok(usuarios);
    }

}

