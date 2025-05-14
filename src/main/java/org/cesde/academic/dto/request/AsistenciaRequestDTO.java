package org.cesde.academic.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.cesde.academic.enums.EstadoAsistencia;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class AsistenciaRequestDTO {

    @NotNull(message = "El ID de la clase es obligatorio")
    private Integer claseId;

    @NotNull(message = "El ID del estudiante es obligatorio")
    private Integer estudianteId;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    @NotNull(message = "El estado es obligatorio")
    private EstadoAsistencia estado;
}
