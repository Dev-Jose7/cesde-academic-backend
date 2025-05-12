package org.cesde.academic.dto.response;

import lombok.*;
import org.cesde.academic.enums.TipoActividad;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class ActividadResponseDTO {

    private final Integer id;
    private final Integer claseId;
    private final String titulo;
    private final String descripcion;
    private final TipoActividad tipo;
    private final LocalDateTime fechaEntrega;
    private final LocalDateTime creado;
    private final LocalDateTime actualizado;
}
