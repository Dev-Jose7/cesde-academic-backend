package org.cesde.academic.model;

import java.io.Serializable;
import java.util.Objects;

// Composite key class for GrupoEstudiante
public class GrupoEstudianteId implements Serializable {

    private Integer grupo;  // Refleja el nombre de la columna en la base de datos (grupo_id)
    private Integer estudiante;  // Refleja el nombre de la columna en la base de datos (estudiante_id)

    public Integer getGrupo() {
        return grupo;
    }

    public void setGrupo(Integer grupo) {
        this.grupo = grupo;
    }

    public Integer getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Integer estudiante) {
        this.estudiante = estudiante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrupoEstudianteId that = (GrupoEstudianteId) o;
        return Objects.equals(grupo, that.grupo) && Objects.equals(estudiante, that.estudiante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grupo, estudiante);
    }
}
