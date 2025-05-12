package org.cesde.academic.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClaseHorarioRequestDTO {

    @NotNull(message = "El ID de la clase es obligatorio")
    private Integer claseId;

    @NotNull(message = "El ID del horario es obligatorio")
    private Integer horarioId;
}
