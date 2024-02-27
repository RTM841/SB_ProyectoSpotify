package com.example.asociaciones.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @Schema(example = "1", description = "Es el identificador del usuario")
    private Long id;

    @Column( unique = true)
    @NotBlank
    @Size(min = 4, max = 16)
    @Schema(example = "Estopa", description = "Es el nombre del usuario , puede ser el nombre normal del usuario o el del artista, todo depende del rol")
    private String username;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Schema(example = "12345", description = "Es la contraseña que el usuario eliga")
    private String password;

    @ManyToMany
    @JoinTable(name ="users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name ="rol_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "rol_id"})}
    )
    private List<Rol> roles;


    private boolean enabled;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean admin;

    @PrePersist
    public void prePresist()
    {
        enabled = true;
    }

    @ManyToMany
    @JoinTable(name = "usuario_cancion",
            joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_cancion", referencedColumnName = "id")
    )
    private List<Cancion> canciones = new ArrayList<>();
}
