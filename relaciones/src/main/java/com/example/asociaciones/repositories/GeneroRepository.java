package com.example.asociaciones.repositories;

import com.example.asociaciones.entity.Cancion;
import com.example.asociaciones.entity.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GeneroRepository  extends JpaRepository<Genero, Long> {
    @Query("SELECT COUNT(c) FROM Cancion c JOIN c.generos g WHERE g.nombre = :nombre")
    int countByGeneronombre(String nombre);

}
