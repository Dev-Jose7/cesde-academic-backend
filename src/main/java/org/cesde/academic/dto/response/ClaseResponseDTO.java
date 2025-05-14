package org.cesde.academic.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class ClaseResponseDTO {

    private final Integer id;
    private final String grupo;
    private final String docente;
    private final String modulo;
    private final LocalDateTime creado;
    private final LocalDateTime actualizado;
}
