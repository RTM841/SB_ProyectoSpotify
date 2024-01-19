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

    private String nombreUsuario;
    private String password;

    @ManyToMany
    @JoinTable(name ="users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name ="rol_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "rol_id"})}
    )
    private List<Rol> roles;

    @ManyToMany
    @JoinTable(name = "usuario_cancion",
            joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_cancion", referencedColumnName = "id")
    )
    private List<Cancion> canciones = new ArrayList<>();
}
