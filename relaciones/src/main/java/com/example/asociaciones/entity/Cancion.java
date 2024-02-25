package com.example.asociaciones.entity;

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
    private Long id;

    @NotBlank
    private String nombre;

    @Temporal(TemporalType.DATE)
    private Date fechaCreación;

    @NotBlank
    private String artistas;


    @ManyToMany
    @JoinTable(name = "cancion_genero",
            joinColumns = @JoinColumn(name = "id_cancion", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_genero", referencedColumnName = "id")
    )
    private List<Genero> generos = new ArrayList<>();
}
