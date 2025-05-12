package org.cesde.academic.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class AsistenciaResponseDTO {

    private final Integer id;
    private final Integer claseId;
    private final Integer estudianteId;
    private final LocalDateTime fecha;
    private final String estado;
    private final LocalDateTime creado;
    private final LocalDateTime actualizado;
}
