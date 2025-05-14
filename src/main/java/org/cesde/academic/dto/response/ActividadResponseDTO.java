package org.cesde.academic.dto.response;

import lombok.*;
import org.cesde.academic.enums.TipoActividad;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;

@AllArgsConstructor
@Getter
@ToString
public class ActividadResponseDTO {

    private final Integer id;
    private final ClaseResponseInfoDTO clase;
    private final String titulo;
    private final String descripcion;
    private final TipoActividad tipo;
    private final LocalDate fechaEntrega;
    private final LocalDateTime creado;
    private final LocalDateTime actualizado;
}
