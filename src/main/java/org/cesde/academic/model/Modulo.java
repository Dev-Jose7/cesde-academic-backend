package org.cesde.academic.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "modulo")
public class Modulo {
    public enum Tipo { MATERIA, CURSO, CATEDRA, SEMINARIO }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "El programa no puede ser nulo")
    @ManyToOne
    @JoinColumn(name = "programa_id", nullable = false)
    private Programa programa;

    @NotNull(message = "El nombre del módulo no puede ser nulo")
    @Size(min = 1, max = 255, message = "El nombre del módulo debe tener entre 1 y 255 caracteres")
    @Column(nullable = false, length = 255)
    private String nombre;

    @NotNull(message = "El tipo de módulo no puede ser nulo")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Tipo tipo;

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

    public @NotNull(message = "El programa no puede ser nulo") Programa getPrograma() {
        return programa;
    }

    public void setPrograma(@NotNull(message = "El programa no puede ser nulo") Programa programa) {
        this.programa = programa;
    }

    public @NotNull(message = "El nombre del módulo no puede ser nulo") @Size(min = 1, max = 255, message = "El nombre del módulo debe tener entre 1 y 255 caracteres") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotNull(message = "El nombre del módulo no puede ser nulo") @Size(min = 1, max = 255, message = "El nombre del módulo debe tener entre 1 y 255 caracteres") String nombre) {
        this.nombre = nombre;
    }

    public @NotNull(message = "El tipo de módulo no puede ser nulo") Tipo getTipo() {
        return tipo;
    }

    public void setTipo(@NotNull(message = "El tipo de módulo no puede ser nulo") Tipo tipo) {
        this.tipo = tipo;
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
        return "Modulo{" +
                "id=" + id +
                ", programa=" + programa +
                ", nombre='" + nombre + '\'' +
                ", tipo=" + tipo +
                ", creado=" + creado +
                ", actualizado=" + actualizado +
                '}';
    }
}
