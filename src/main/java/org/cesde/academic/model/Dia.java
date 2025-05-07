package org.cesde.academic.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "dia")
public class Dia {

    public enum Nombre {
        LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @NotNull(message = "El nombre no puede ser nulo")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true, length = 50)
    private Nombre nombre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public void setNombre(Nombre nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Dia{" +
                "id=" + id +
                ", nombre=" + nombre +
                '}';
    }
}