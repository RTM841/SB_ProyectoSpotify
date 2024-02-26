package com.example.asociaciones.repositories;

import com.example.asociaciones.entity.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface CancionRepository extends JpaRepository<Cancion, Long> {

    List<Cancion> findByFechaCreaciónBetween(Date fechaIni, Date fechaFin);

    List<Cancion> findCancionByNombre(String nombre);

    @Query("SELECT c FROM Cancion c JOIN c.generos g WHERE g.Nombre = :Nombre")
    List<Cancion> findByGeneroNombre(String Nombre);

    @Query("SELECT c FROM Cancion c order by c.fechaCreación desc")
    List<Cancion> findByFechaCreación();

}
