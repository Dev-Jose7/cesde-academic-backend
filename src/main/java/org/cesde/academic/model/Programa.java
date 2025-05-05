package org.cesde.academic.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "programa")
public class Programa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "La escuela no puede ser nula")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "escuela_id", nullable = false)
    private Escuela escuela;

    @NotNull(message = "El nombre del programa no puede ser nulo")
    @Size(min = 1, max = 255, message = "El nombre del programa debe tener entre 1 y 255 caracteres")
    @Column(nullable = false, length = 255)
    private String nombre;

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

    public Escuela getEscuela() {
        return escuela;
    }

    public void setEscuela(Escuela escuela) {
        this.escuela = escuela;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        return "Programa{" +
                "id=" + id +
                ", escuela=" + (escuela != null ? escuela.getId() : null) +
                ", nombre='" + nombre + '\'' +
                ", creado=" + creado +
                ", actualizado=" + actualizado +
                '}';
    }
}
