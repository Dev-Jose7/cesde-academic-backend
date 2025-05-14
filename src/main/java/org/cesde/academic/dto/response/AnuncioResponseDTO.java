package org.cesde.academic.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class AnuncioResponseDTO {

    private final Integer id;
    private final ClaseResponseInfoDTO clase;
    private final String titulo;
    private final String mensaje;
    private final LocalDate fecha;
    private final LocalDateTime creado;
    private final LocalDateTime actualizado;
}
