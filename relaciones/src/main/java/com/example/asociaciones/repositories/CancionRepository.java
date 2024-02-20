package com.example.asociaciones.repositories;

import com.example.asociaciones.entity.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancionRepository extends JpaRepository<Cancion, Long> {
}
