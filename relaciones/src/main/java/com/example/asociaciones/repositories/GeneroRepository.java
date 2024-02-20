package com.example.asociaciones.repositories;

import com.example.asociaciones.entity.Cancion;
import com.example.asociaciones.entity.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepository  extends JpaRepository<Genero, Long> {
}
