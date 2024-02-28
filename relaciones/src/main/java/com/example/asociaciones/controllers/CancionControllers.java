package com.example.asociaciones.controllers;

import com.example.asociaciones.entity.Cancion;
import com.example.asociaciones.services.CancionService;
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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


import javax.naming.NameNotFoundException;
import java.nio.file.AccessDeniedException;
import java.util.*;


@RestController
@RequestMapping("/api/canciones")
@Tag(name = "cancion", description = "Este es el controlador de canciones como su nombre indica , aqui se haran las operaciones CRUD de canciones")
public class CancionControllers {

        @Autowired
        private CancionService cancionService;

        @ApiResponses(value = {
                @ApiResponse(responseCode = "200",
                        description = "Obtene una lista de canciones",
                        content = { @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = Cancion.class)))}),

                @ApiResponse(responseCode = "403",
                        description = "Error cuando no se tiene permiso o se ejecutado mal el método",
                        content = { @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
        })
        @Operation(summary = "findAll", description = "Devuelve una lista de las canciones")
        @GetMapping
        public List<Cancion> list(){
            return cancionService.findAll();
        }

        @ApiResponses(value = {
                @ApiResponse(responseCode = "200",
                        description = "Obtene una lista de canciones, filtradas por el Id",
                        content = { @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = Cancion.class)))}),

                @ApiResponse(responseCode = "403",
                        description = "Error cuando no se tiene permiso o se ejecutado mal el método",
                        content = { @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
        })
        @Operation(summary = "vista", description = "Devuelve una lista de las canciones por su id")
        @GetMapping("/{id}")
        public ResponseEntity<Cancion> view(@PathVariable Long id){
            Optional<Cancion> productOptional = cancionService.findById(id);
            if(productOptional.isPresent()){
                return ResponseEntity.ok(productOptional.orElseThrow());
            }
            return ResponseEntity.notFound().build();
        }


        @ApiResponses(value = {
                @ApiResponse(responseCode = "200",
                        description = "Se ha creado de forma correcta una canción",
                        content = { @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = Cancion.class)))}),

                @ApiResponse(responseCode = "403",
                        description = "No se ha creado de forma correcta la canción",
                        content = { @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
        })
        @Operation(summary = "create", description = "Crea la canción")
        @PostMapping
        public ResponseEntity<Cancion> create(@RequestBody Cancion cancion){
            return ResponseEntity.status(HttpStatus.CREATED).body(cancionService.save(cancion));
        }


        @ApiResponses(value = {
                @ApiResponse(responseCode = "200",
                        description = "Se ha actualizado de forma correcta una canción",
                        content = { @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = Cancion.class)))}),

                @ApiResponse(responseCode = "403",
                        description = "No se ha actualizado de forma correcta la canción",
                        content = { @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
        })
        @Operation(summary = "update", description = "Actualiza la canción")
        @PutMapping("/{id}")
        public ResponseEntity<Cancion> update(@PathVariable Long id, @RequestBody Cancion product){
            Optional <Cancion> productOptional = cancionService.update(id, product);
            if(productOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
            }
            return ResponseEntity.notFound().build();
        }


        @ApiResponses(value = {
                @ApiResponse(responseCode = "200",
                        description = "Se ha borrado de forma correcta una canción",
                        content = { @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = Cancion.class)))}),

                @ApiResponse(responseCode = "403",
                        description = "No se ha borrado de forma correcta la canción",
                        content = { @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
        })
        @Operation(summary = "delete", description = "Borra la canción")
        @DeleteMapping("/{id}")
        public ResponseEntity<Cancion> delete(@PathVariable Long id){
            Optional<Cancion> productOptional = cancionService.delete(id);
            if(productOptional.isPresent()){
                return ResponseEntity.ok(productOptional.orElseThrow());
            }
            return ResponseEntity.notFound().build();
        }


        @ApiResponses(value = {
                @ApiResponse(responseCode = "200",
                        description = "Devuelve las canciones situadas entre las dos fechas",
                        content = { @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = Cancion.class)))}),

                @ApiResponse(responseCode = "403",
                        description = "No se ha encontrado o la fecha esta erronea",
                        content = { @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
        })
        @Operation(summary = "findUserBeteenDates", description = "Devuelve una lista de canciones comprendidas entre dos fechas")
        @GetMapping("/betweenDates")
        public ResponseEntity<?> getCancionBetweenDates(@RequestParam("fechaIni") @DateTimeFormat(pattern = "yyyy-MM-DD") Date fechaIni,
                                                      @RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-DD") Date fechaFin) {
            List<Cancion> canciones = cancionService.findByFechaCreacionBetween(fechaIni, fechaFin);
            if(canciones.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado ninguna cancion");
            }
            return ResponseEntity.ok(canciones);
        }

        @ApiResponses(value = {
                @ApiResponse(responseCode = "200",
                        description = "Se devuelve la canción que se ha buscado mediane el nombre",
                        content = { @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = Cancion.class)))}),

                @ApiResponse(responseCode = "403",
                        description = "No se encuentra la cacnción o el nombre está mal",
                        content = { @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
        })
        @Operation(summary = "findCancionByNombre", description = "Devuelve una lista de canciones con ese nombre debido a que pueden ser varias con el mismo nombre pero de diferentes fechas")
        @GetMapping("/nombre/{nombre}")
        public ResponseEntity<?> getCancionByNombre(@PathVariable String nombre){

            try{
                List<Cancion> canciones = cancionService.findCancionByPartialNombre(nombre);
                if(canciones.isEmpty()){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado ninguna cancion con este nombre: " + nombre);
                }else {
                    return ResponseEntity.ok(canciones);
                }


            }catch (EmptyResultDataAccessException e){
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", e.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }

        }

        @ApiResponses(value = {
                @ApiResponse(responseCode = "200",
                        description = "Se a listado todas las canciones de un genero",
                        content = { @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = Cancion.class)))}),

                @ApiResponse(responseCode = "403",
                        description = "No se ha encontrado nada o el nombre esta incorrecto",
                        content = { @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
        })
        @Operation(summary = "findGeneroNombre", description = "Devuelve las canciones que tiene un género, buscando por el nombre del mismo")
        @GetMapping("/genero/{Nombre}")
        public ResponseEntity<?> getByGeneroNombre(@PathVariable String Nombre){

            try{
                List<Cancion> canciones = cancionService.findByGeneroNombre(Nombre);
                return ResponseEntity.ok(canciones);

            }catch (EmptyResultDataAccessException e){
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", e.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }

        }

        @ApiResponses(value = {
                @ApiResponse(responseCode = "200",
                        description = "Se muestra una lista de todas las canciones ordenadas por la fecha de forma más reciente",
                        content = { @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = Cancion.class)))}),

                @ApiResponse(responseCode = "403",
                        description = "No se ha encontrado nada o la fecha esta incorrecto",
                        content = { @Content(mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = Message.class)))})
        })
        @Operation(summary = "findFechaCreacion", description = "Devuelve la lista de canciones ordenada de forma más reciente")
        @GetMapping("/fechaReciente")
        public ResponseEntity<?> getByFechaCreacion(){
            try{
                List<Cancion> canciones = cancionService.findByFechaCreacion();
                return ResponseEntity.ok(canciones);

            }catch (EmptyResultDataAccessException e){
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", e.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }

        }
}

