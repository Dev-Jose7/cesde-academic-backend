package org.cesde.academic.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class ProgramaResponseDTO {
    private final Integer id;
    private final Integer escuelaId;
    private final String nombre;
    private final LocalDateTime creado;
    private final LocalDateTime actualizado;
}
