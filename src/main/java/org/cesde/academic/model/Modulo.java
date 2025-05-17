package org.cesde.academic.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.cesde.academic.enums.NivelModulo;
import org.cesde.academic.enums.TipoModulo;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "modulo")
public class Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)  //Swagger: no pedirá actualizado en el cuerpo de entrada.
    private Integer id;

    @NotNull(message = "El nombre del módulo no puede ser nulo")
    @Size(min = 1, max = 255, message = "El nombre del módulo debe tener entre 1 y 255 caracteres")
    @Column(nullable = false)
    private String nombre;

    @NotNull(message = "El tipo de módulo no puede ser nulo")
    @Enumerated(EnumType.STRING) // Guarda el nombre del valor del enum como una cadena de texto en la base de datos.
    @Column(nullable = false, length = 50)
    private TipoModulo tipo;

    @NotNull(message = "El nivel de módulo no puede ser nulo")
    @Enumerated(EnumType.STRING) // Guarda el nombre del valor del enum como una cadena de texto en la base de datos.
    @Column(nullable = false, length = 50)
    private NivelModulo nivel;

    @CreationTimestamp
    @Column(name = "creado", nullable = false, updatable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) //Jackson: ignorará clave para esta propiedad que venga en el JSON.
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)  //Swagger: no pedirá actualizado en el cuerpo de entrada.
    private LocalDateTime creado;

    @UpdateTimestamp
    @Column(name = "actualizado", nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) //Jackson: ignorará clave para esta propiedad que venga en el JSON.
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)  //Swagger: no pedirá actualizado en el cuerpo de entrada.
    private LocalDateTime actualizado;

    // Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoModulo getTipo() {
        return tipo;
    }

    public void setTipo(TipoModulo tipo) {
        this.tipo = tipo;
    }

    public NivelModulo getNivel() {
        return nivel;
    }

    public void setNivel(NivelModulo nivel) {
        this.nivel = nivel;
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
                ", nombre='" + nombre + '\'' +
                ", tipo=" + tipo +
                ", creado=" + creado +
                ", actualizado=" + actualizado +
                '}';
    }
}
