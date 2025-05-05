package org.cesde.academic.model;

import java.io.Serializable;
import java.util.Objects;

public class ClaseHorarioId implements Serializable {

    private Integer clase;
    private Integer horario;

    public ClaseHorarioId() {}

    public ClaseHorarioId(Integer clase, Integer horario) {
        this.clase = clase;
        this.horario = horario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClaseHorarioId)) return false;
        ClaseHorarioId that = (ClaseHorarioId) o;
        return Objects.equals(clase, that.clase) &&
                Objects.equals(horario, that.horario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clase, horario);
    }
}
