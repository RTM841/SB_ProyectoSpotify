package com.example.asociaciones.controllers;

import com.example.asociaciones.entity.Cancion;
import com.example.asociaciones.services.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CancionControllers {
    @RestController
    @RequestMapping("/api/products")
    public class ProductController {

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
        public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result){
            if (result.hasFieldErrors()){
                return validation(result);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(cancionService.save(product));
        }

        @PutMapping("/{id}")
        public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Product product, BindingResult result){
            if (result.hasFieldErrors()){
                return validation(result);
            }
            Optional <Product> productOptional = cancionService.update(id, product);
            if(productOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
            }
            return ResponseEntity.notFound().build();
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Product> delete(@PathVariable Long id){
            Optional<Product> productOptional = cancionService.delete(id);
            if(productOptional.isPresent()){
                return ResponseEntity.ok(productOptional.orElseThrow());
            }
            return ResponseEntity.notFound().build();
        }

        private ResponseEntity<?> validation(BindingResult result){
            Map<String, String> errors = new HashMap<>();

            result.getFieldErrors().forEach(err ->{
                errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errors);
        }


    }
}
