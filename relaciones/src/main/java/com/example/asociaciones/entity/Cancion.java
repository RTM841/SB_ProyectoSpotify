package com.example.asociaciones.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "canciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "1", description = "Es el identificador de la canción , numerico , autoincremental")
    private Long id;

    @Schema(example = "25/8", description = "Es el título de la canció, alfabético")
    @NotBlank
    private String nombre;

    @Schema(example = "2020-02-29", description = "Es la fecha de creación de la canción, tiene el formato yyyy-MM-DD")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @Schema(example = "Bad Bunny", description = "Se pone el nombre del artista principal, alfábetico")
    @NotBlank
    private String artistas;


    @ManyToMany
    @JoinTable(name = "cancion_genero",
            joinColumns = @JoinColumn(name = "id_cancion", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_genero", referencedColumnName = "id")
    )
    private List<Genero> generos = new ArrayList<>();
}
