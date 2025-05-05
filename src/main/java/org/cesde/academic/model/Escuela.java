package org.cesde.academic.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "escuela")
public class Escuela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "El nombre de la escuela no puede ser nulo")
    @Size(min = 1, max = 255, message = "El nombre de la escuela debe tener entre 1 y 255 caracteres")
    @Column(nullable = false, length = 255)
    private String nombre;

    @CreationTimestamp
    private LocalDateTime creado;

    @UpdateTimestamp
    private LocalDateTime actualizado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull(message = "El nombre de la escuela no puede ser nulo") @Size(min = 1, max = 255, message = "El nombre de la escuela debe tener entre 1 y 255 caracteres") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotNull(message = "El nombre de la escuela no puede ser nulo") @Size(min = 1, max = 255, message = "El nombre de la escuela debe tener entre 1 y 255 caracteres") String nombre) {
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
        return "Escuela{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", creado=" + creado +
                ", actualizado=" + actualizado +
                '}';
    }
}

