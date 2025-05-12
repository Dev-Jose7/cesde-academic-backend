package org.cesde.academic.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
public class ClaseRequestDTO {

    @NotNull(message = "El ID del grupo es obligatorio")
    private Integer grupoId;

    @NotNull(message = "El ID del docente es obligatorio")
    private Integer docenteId;

    @NotNull(message = "El ID del módulo es obligatorio")
    private Integer moduloId;
}
