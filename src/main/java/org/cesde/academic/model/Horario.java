package org.cesde.academic.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "horario")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @NotNull(message = "El día no puede ser nulo")
    @ManyToOne
    @JoinColumn(name = "dia_id", nullable = false)
    private Dia dia;

    @NotNull(message = "La franja horaria no puede ser nula")
    @ManyToOne
    @JoinColumn(name = "franja_id", nullable = false)
    private FranjaHoraria franjaHoraria;

    // Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }

    public FranjaHoraria getFranjaHoraria() {
        return franjaHoraria;
    }

    public void setFranjaHoraria(FranjaHoraria franjaHoraria) {
        this.franjaHoraria = franjaHoraria;
    }

    @Override
    public String toString() {
        return "Horario{" +
                "id=" + id +
                ", dia=" + (dia != null ? dia.getNombre() : null) +
                ", franjaHoraria=" + franjaHoraria +
                '}';
    }
}