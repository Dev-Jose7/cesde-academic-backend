package org.cesde.academic.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class GrupoEstudianteResponseDTO {

    private final Integer grupoId;
    private final Integer estudianteId;
    private final LocalDateTime creado;
    private final LocalDateTime actualizado;
}
