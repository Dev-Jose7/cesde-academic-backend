package org.cesde.academic.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "horario")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @CreationTimestamp
    @Column(name = "creado", nullable = false, updatable = false)
    private LocalDateTime creado;

    @UpdateTimestamp
    @Column(name = "actualizado", nullable = false)
    private LocalDateTime actualizado;

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

    public LocalDateTime getCreado() {
        return creado;
    }

    public void setCreado(LocalDateTime creado) {
        this.creado = creado;
    }

    public LocalDateTime getActualizado() {
        return actualizado;
    }

    public void setActualizado(LocalDateTime actualizado) {
        this.actualizado = actualizado;
    }

    @Override
    public String toString() {
        return "Horario{" +
                "id=" + id +
                ", dia=" + dia +
                ", franjaHoraria=" + franjaHoraria +
                ", creado=" + creado +
                ", actualizado=" + actualizado +
                '}';
    }
}