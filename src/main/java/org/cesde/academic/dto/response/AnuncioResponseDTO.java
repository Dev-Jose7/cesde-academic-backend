package org.cesde.academic.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class AnuncioResponseDTO {

    private final Integer id;
    private final Integer claseId;
    private final String titulo;
    private final String mensaje;
    private final LocalDateTime fecha;
    private final LocalDateTime creado;
    private final LocalDateTime actualizado;
}
