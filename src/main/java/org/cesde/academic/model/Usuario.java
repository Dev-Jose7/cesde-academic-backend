package org.cesde.academic.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
public class Usuario {
    public enum Tipo { ESTUDIANTE, DOCENTE, ADMINISTRATIVO, DIRECTIVO }
    public enum Estado { ACTIVO, INACTIVO, SUSPENDIDO, ELIMINADO }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) //Jackson: ignorará clave id que venga en el JSON.
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)  //Swagger: no pedirá actualizado en el cuerpo de entrada.
    private Integer id;

    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 1, max = 255, message = "El nombre debe tener entre 1 y 255 caracteres")
    @Column(nullable = false)
    private String nombre;

    @NotNull(message = "El correo no puede ser nulo")
    @Email(message = "El correo debe ser válido")
    @Size(max = 255, message = "El correo no puede exceder los 255 caracteres")
    @Column(nullable = false, unique = true)
    private String correo;

    @NotNull(message = "La contraseña no puede ser nula")
    @Size(min = 6, max = 255, message = "La contraseña debe tener al menos 6 caracteres y máximo 255")
    @Column(name = "contraseña", nullable = false)
    private String contrasena; // Renombrado de "contraseña" a "contrasena" (opcional)

    @NotNull(message = "El estado no puede ser nulo")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Tipo tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Estado estado;

    @CreationTimestamp
    @Column(name = "creado", nullable = false, updatable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) //Jackson: ignorará valor de una clave para esta propiedad que venga en el JSON.
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)  //Swagger: no pedirá actualizado en el cuerpo de entrada.
    private LocalDateTime creado; // Hibernate/JPA: seguirá asignando automáticamente el valor en cada creación.

    @UpdateTimestamp
    @Column(name = "actualizado", nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) //Jackson: ignorará valor de una clave para esta propiedad que venga en el JSON.
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)  //Swagger: no pedirá actualizado en el cuerpo de entrada.
    private LocalDateTime actualizado; // Hibernate/JPA: seguirá asignando automáticamente el valor en cada actualización.

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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
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
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", tipo=" + tipo +
                ", estado=" + estado +
                ", creado=" + creado +
                ", actualizado=" + actualizado +
                '}';
    }
}