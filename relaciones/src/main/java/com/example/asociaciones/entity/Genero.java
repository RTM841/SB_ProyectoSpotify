package com.example.asociaciones.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "genero")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "1", description = "Es el identificador del genero, numerico , autoincremental")
    private Long id;

    @Schema(example = "Pop", description = "Es el nombre del género al que pertencen las canciones")
    private String nombre;


}
