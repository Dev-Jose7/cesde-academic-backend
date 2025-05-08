package org.cesde.academic.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "anuncio")
public class Anuncio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)  //Swagger: no pedirá actualizado en el cuerpo de entrada.
    private Integer id;

    @NotNull(message = "La clase no puede ser nula")
    @ManyToOne
    @JoinColumn(name = "clase_id", nullable = false)
    private Clase clase;

    @NotNull(message = "El título no puede ser nulo")
    @Size(min = 1, max = 255, message = "El título debe tener entre 1 y 255 caracteres")
    @Column(nullable = false, length = 255)
    private String titulo;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String mensaje;

    @NotNull(message = "La fecha no puede ser nula")
    @Column(nullable = false)
    private LocalDateTime fecha;

    @CreationTimestamp
    @Column(name = "creado", nullable = false, updatable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) //Jackson: ignorará clave id que venga en el JSON.
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)  //Swagger: no pedirá actualizado en el cuerpo de entrada.
    private LocalDateTime creado;

    @UpdateTimestamp
    @Column(name = "actualizado", nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) //Jackson: ignorará clave id que venga en el JSON.
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)  //Swagger: no pedirá actualizado en el cuerpo de entrada.
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
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
        return "Anuncio{" +
                "id=" + id +
                ", clase=" + (clase != null ? clase.getId() : null) +
                ", titulo='" + titulo + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", fecha=" + fecha +
                ", creado=" + creado +
                ", actualizado=" + actualizado +
                '}';
    }
}