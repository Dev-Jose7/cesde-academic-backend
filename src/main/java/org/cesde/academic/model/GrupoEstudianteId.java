package org.cesde.academic.model;

import java.io.Serializable;
import java.util.Objects;

// Composite key class for GrupoEstudiante
public class GrupoEstudianteId implements Serializable {

    private Integer grupoId;  // Refleja el nombre de la columna en la base de datos (grupo_id)
    private Integer estudianteId;  // Refleja el nombre de la columna en la base de datos (estudiante_id)

    public Integer getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Integer grupoId) {
        this.grupoId = grupoId;
    }

    public Integer getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Integer estudianteId) {
        this.estudianteId = estudianteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrupoEstudianteId that = (GrupoEstudianteId) o;
        return Objects.equals(grupoId, that.grupoId) && Objects.equals(estudianteId, that.estudianteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grupoId, estudianteId);
    }
}
