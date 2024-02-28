package com.example.asociaciones.repositories;

import com.example.asociaciones.entity.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface CancionRepository extends JpaRepository<Cancion, Long> {

    List<Cancion> findByFechaCreacionBetween(Date fechaIni, Date fechaFin);

    List<Cancion> findByNombreContainingIgnoreCase(String nombre);

    @Query("SELECT c FROM Cancion c JOIN c.generos g WHERE g.nombre = :nombre")
    List<Cancion> findByGeneroNombre(String nombre);

    @Query("SELECT c FROM Cancion c order by c.fechaCreacion desc")
    List<Cancion> findByFechaCreacion();

}
