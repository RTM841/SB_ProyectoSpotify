package com.example.asociaciones.repositories;

import com.example.asociaciones.entity.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CancionRepository extends JpaRepository<Cancion, Long> {

    List<Cancion> findByFechaCreaciónBetween(Date fechaIni, Date fechaFin);
}
