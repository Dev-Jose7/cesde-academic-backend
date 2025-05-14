package org.cesde.academic.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Column(name = "creado", nullable = false, updatable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) //Jackson: ignorará clave para esta propiedad que venga en el JSON.
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)  //Swagger: no pedirá actualizado en el cuerpo de entrada.
    private LocalDateTime creado;

    @UpdateTimestamp
    @Column(name = "actualizado", nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) //Jackson: ignorará clave para esta propiedad que venga en el JSON.
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)  //Swagger: no pedirá actualizado en el cuerpo de entrada.
    private LocalDateTime actualizado;

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Usuario getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Usuario estudiante) {
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

// GrupoEstudiante usa una clave compuesta (grupo_id, estudiante_id). Eso significa que ambas claves forman el
// identificador primario. Como consecuencia:
// No puedes cambiar los valores de la clave primaria en una actualización (PUT), porque JPA trata el ID
// como inmutable una vez persistido.
// ¿Cómo manejarlo correctamente?: Eliminar y volver a crear con las nuevas claves
// Este es el enfoque más limpio y seguro en estos casos.
// La clave primaria compuesta se generará a través de una clase el cual recibirá los datos de la claves foraneas
// La instancia que se generé de esta clase será la clave primeria compuesta y con esta instancia se puede hacer
// las consultas usando los repositorios