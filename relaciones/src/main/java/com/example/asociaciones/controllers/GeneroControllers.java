package com.example.asociaciones.controllers;


import com.example.asociaciones.entity.Genero;
import com.example.asociaciones.services.GeneroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/generos")
@Tag(name = "genero", description = "Este es el controlador de generos como su nombre indica , aqui se haran las operaciones CRUD de generos")
public class GeneroControllers {

    @Autowired
    private GeneroService generoService;


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Obtene una lista de los generos",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Genero.class)))}),

            @ApiResponse(responseCode = "403",
                    description = "Error cuando no se tiene permiso o se ejecutado mal el método",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
    })
    @Operation(summary = "findAll", description = "Devuelve una lista de los generos")
    @GetMapping
    public List<Genero> list(){return generoService.findAll();}


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Obtene una lista de los generos filtrados por el id",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Genero.class)))}),

            @ApiResponse(responseCode = "403",
                    description = "Error cuando no se tiene permiso o se ejecutado mal el método",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
    })
    @Operation(summary = "findAll", description = "Devuelve una lista de los generos buscando los mismo mediante el id")
    @GetMapping("/{id}")
    public ResponseEntity<Genero> view(@PathVariable Long id){
        Optional<Genero> productOptional = generoService.findById(id);
        if(productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Crea un género",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Genero.class)))}),

            @ApiResponse(responseCode = "403",
                    description = "Error cuando no se tiene permiso o se ejecutado mal el método",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
    })
    @Operation(summary = "create", description = "Acción mediante la cuál creamos un género")
    @PostMapping
    public ResponseEntity<Genero> create(@RequestBody Genero genero){
        return ResponseEntity.status(HttpStatus.CREATED).body(generoService.save(genero));
    }



    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Actualiza un género",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Genero.class)))}),

            @ApiResponse(responseCode = "403",
                    description = "Error cuando no se tiene permiso o se ejecutado mal el método",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
    })
    @Operation(summary = "update", description = "Acción mediante la cuál actualizamos un género")
    @PutMapping("/{id}")
    public ResponseEntity<Genero> update(@PathVariable Long id, @RequestBody Genero genero){
        Optional <Genero> productOptional = generoService.update(id, genero);
        if(productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }



    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Eliminamos un género",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Genero.class)))}),

            @ApiResponse(responseCode = "403",
                    description = "Error cuando no se tiene permiso o se ejecutado mal el método",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
    })
    @Operation(summary = "delete", description = "Acción mediante la cuál eliminamos un género mediante su id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Genero> delete(@PathVariable Long id){
        Optional<Genero> productOptional = generoService.delete(id);
        if(productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Nos devuelve un número , que son las canciones de cada genero",
                    content = { @Content(mediaType = "text",
                            array = @ArraySchema(schema = @Schema(implementation = Genero.class)))}),

            @ApiResponse(responseCode = "403",
                    description = "Error cuando no se tiene permiso o se ejecutado mal el método",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
    })
    @Operation(summary = "countByGeneroNombre", description = "Nos devuelve un entero que es el número de canciones que tiene un género y buscamos el número mediante el nombre del mismo")
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Integer> view(@PathVariable String nombre){
        try{
            int numero = generoService.countByGeneronombre(nombre);
            return ResponseEntity.ok(numero);

        }catch (EmptyResultDataAccessException e){
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse.size());
        }
    }

}
