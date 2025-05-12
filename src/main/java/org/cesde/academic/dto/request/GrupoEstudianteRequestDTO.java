package org.cesde.academic.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
public class GrupoEstudianteRequestDTO {

    @NotNull(message = "El ID del grupo es obligatorio")
    private Integer grupoId;

    @NotNull(message = "El ID del estudiante es obligatorio")
    private Integer estudianteId;
}

