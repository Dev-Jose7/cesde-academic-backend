package org.cesde.academic.model;

import java.io.Serializable;
import java.util.Objects;

// Composite key class for GrupoEstudiante
public class GrupoEstudianteId implements Serializable {
    private Integer grupo;
    private Integer estudiante;

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
