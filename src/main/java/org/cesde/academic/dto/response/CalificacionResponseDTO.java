package org.cesde.academic.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class CalificacionResponseDTO {

    private final Integer id;
    private final Integer actividadId;
    private final Integer estudianteId;
    private final LocalDateTime fecha;
    private final BigDecimal nota;
    private final LocalDateTime creado;
    private final LocalDateTime actualizado;
}
