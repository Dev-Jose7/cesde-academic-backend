package org.cesde.academic.dto.response;

import lombok.*;
import org.cesde.academic.enums.TipoActividad;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class CalificacionResponseDTO {

    private final Integer id;
    private final ActividadResponseInfoDTO actividad;
    private final String estudiante;
    private final LocalDate fecha;
    private final BigDecimal nota;
    private final LocalDateTime creado;
    private final LocalDateTime actualizado;
}
