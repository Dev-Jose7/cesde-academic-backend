package org.cesde.academic.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class ClaseResponseDTO {

    private final Integer id;
    private final Integer grupoId;
    private final Integer docenteId;
    private final Integer moduloId;
    private final LocalDateTime creado;
    private final LocalDateTime actualizado;
}
