package org.cesde.academic.dto.response;

import lombok.*;
import org.cesde.academic.enums.EstadoAsistencia;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

@AllArgsConstructor
@Getter
@ToString
public class AsistenciaResponseDTO {

    private final Integer id;
    private final ClaseResponseInfoDTO clase;
    private final String estudiante;
    private final LocalDate fecha;
    private final EstadoAsistencia estado;
    private final LocalDateTime creado;
    private final LocalDateTime actualizado;
}
