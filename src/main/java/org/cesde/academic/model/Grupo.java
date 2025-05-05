package org.cesde.academic.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "grupo")
public class Grupo {
    public enum Estado { ACTIVO, FINALIZADO }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "El programa no puede ser nulo")
    @ManyToOne
    @JoinColumn(name = "programa_id", nullable = false)
    private Programa programa;

    @NotNull(message = "El semestre no puede ser nulo")
    @ManyToOne
    @JoinColumn(name = "semestre_id", nullable = false)
    private Semestre semestre;

    @NotNull(message = "El estado del grupo no puede ser nulo")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Estado estado;

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

    public @NotNull(message = "El semestre no puede ser nulo") Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(@NotNull(message = "El semestre no puede ser nulo") Semestre semestre) {
        this.semestre = semestre;
    }

    public @NotNull(message = "El estado del grupo no puede ser nulo") Estado getEstado() {
        return estado;
    }

    public void setEstado(@NotNull(message = "El estado del grupo no puede ser nulo") Estado estado) {
        this.estado = estado;
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
        return "Grupo{" +
                "id=" + id +
                ", programa=" + programa +
                ", semestre=" + semestre +
                ", estado=" + estado +
                ", creado=" + creado +
                ", actualizado=" + actualizado +
                '}';
    }
}
