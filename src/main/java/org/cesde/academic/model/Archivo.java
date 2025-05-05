package org.cesde.academic.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "archivo")
public class Archivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Relación con la entidad Clase
    @ManyToOne
    @JoinColumn(name = "clase_id", nullable = false)
    private Clase clase;

    // Relación con la entidad Usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Size(max = 255, message = "El nombre del archivo no puede superar los 255 caracteres")
    @Column(name = "nombre_archivo", length = 255)
    private String nombreArchivo;

    @Size(max = 500, message = "La ruta del archivo no puede superar los 500 caracteres")
    @Column(name = "ruta_archivo", length = 500)
    private String rutaArchivo;

    @NotNull(message = "La fecha de subida es obligatoria")
    @Column(name = "fecha_subida", nullable = false)
    private LocalDateTime fechaSubida;

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

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public LocalDateTime getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(LocalDateTime fechaSubida) {
        this.fechaSubida = fechaSubida;
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
        return "Archivo{" +
                "id=" + id +
                ", clase=" + clase +
                ", usuario=" + usuario +
                ", nombreArchivo='" + nombreArchivo + '\'' +
                ", rutaArchivo='" + rutaArchivo + '\'' +
                ", fechaSubida=" + fechaSubida +
                ", creado=" + creado +
                ", actualizado=" + actualizado +
                '}';
    }
}
