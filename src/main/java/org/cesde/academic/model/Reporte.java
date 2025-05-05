package org.cesde.academic.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "reporte")
public class Reporte {

    public enum Estado {
        PENDIENTE, EN_PROGRESO, COMPLETADO, CANCELADO, ARCHIVADO, APROBADO, RECHAZADO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "La clase no puede ser nula")
    @ManyToOne
    @JoinColumn(name = "clase_id", nullable = false)
    private Clase clase;

    @NotNull(message = "El usuario no puede ser nulo")
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @NotNull(message = "La descripción no puede ser nula")
    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @NotNull(message = "La fecha no puede ser nula")
    @Column(nullable = false)
    private LocalDateTime fecha;

    @NotNull(message = "El estado no puede ser nulo")
    @Size(max = 50)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Estado estado = Estado.PENDIENTE;

    @CreationTimestamp
    @Column(name = "creado", nullable = false, updatable = false)
    private LocalDateTime creado;

    @UpdateTimestamp
    @Column(name = "actualizado", nullable = false)
    private LocalDateTime actualizado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
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
        return "Reporte{" +
                "id=" + id +
                ", clase=" + (clase != null ? clase.getId() : null) +
                ", usuario=" + (usuario != null ? usuario.getId() : null) +
                ", descripcion='" + descripcion + '\'' +
                ", fecha=" + fecha +
                ", estado=" + estado +
                ", creado=" + creado +
                ", actualizado=" + actualizado +
                '}';
    }
}
