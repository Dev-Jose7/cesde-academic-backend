package org.cesde.academic.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "grupo_estudiante")
@IdClass(GrupoEstudianteId.class)
public class GrupoEstudiante {
    @Id
    @NotNull(message = "El grupo no puede ser nulo")
    @ManyToOne
    @JoinColumn(name = "grupo_id", nullable = false)
    private Grupo grupo;

    @Id
    @NotNull(message = "El estudiante no puede ser nulo")
    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Usuario estudiante;

    @CreationTimestamp
    private LocalDateTime creado;

    @UpdateTimestamp
    private LocalDateTime actualizado;

    public @NotNull(message = "El grupo no puede ser nulo") Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(@NotNull(message = "El grupo no puede ser nulo") Grupo grupo) {
        this.grupo = grupo;
    }

    public @NotNull(message = "El estudiante no puede ser nulo") Usuario getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(@NotNull(message = "El estudiante no puede ser nulo") Usuario estudiante) {
        this.estudiante = estudiante;
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
        return "GrupoEstudiante{" +
                "grupo=" + grupo +
                ", estudiante=" + estudiante +
                ", creado=" + creado +
                ", actualizado=" + actualizado +
                '}';
    }
}
