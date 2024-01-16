package com.example.asociaciones.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "empleado")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 300)
    private String nombre;

    private Integer edad;

    private Boolean casado;

    private Double salario;

    private Instant fechaCreacion;

    private LocalDate fechaCumpleaños;

    private LocalDateTime fechaRegistro;

    @ElementCollection
    private List<String> email = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private EmpleadoTipo categoria;

    @OneToOne
    @JoinColumn(name = "id_direccion", foreignKey = @ForeignKey(name = "fk_empleado_direccion"))
    private Direccion direccion;

    @ManyToOne
    @JoinColumn(name = "id_empresa", foreignKey = @ForeignKey(name = "fk_empleado_empresa"))
    private Empresa empresa;


    @ManyToMany
    @JoinTable(name = "empleado_proyecto",
            joinColumns = @JoinColumn(name = "id_empleado", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_proyecto", referencedColumnName = "id")
    )
    private List<Proyecto> proyectos = new ArrayList<>();
}