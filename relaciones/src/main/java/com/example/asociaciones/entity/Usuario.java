package com.example.asociaciones.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private String Apellidos;
    private Date fechaNacimiento;
    private int oyentes;

    @ManyToOne
    @JoinColumn(name = "id_rol", foreignKey = @ForeignKey(name = "fk_usuario_rol"))
    private Rol rol;

    @ManyToMany
    @JoinTable(name = "usuario_cancion",
            joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_cancion", referencedColumnName = "id")
    )
    private List<Cancion> canciones = new ArrayList<>();
}
