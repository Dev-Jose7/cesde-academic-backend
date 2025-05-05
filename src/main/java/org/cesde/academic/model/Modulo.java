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

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
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
                ", programa=" + (programa != null ? programa.getId() : null) +
                ", nombre='" + nombre + '\'' +
                ", tipo=" + tipo +
                ", creado=" + creado +
                ", actualizado=" + actualizado +
                '}';
    }
}
