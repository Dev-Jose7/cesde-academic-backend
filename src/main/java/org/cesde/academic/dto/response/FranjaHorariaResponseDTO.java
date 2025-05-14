package org.cesde.academic.dto.response;

import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class FranjaHorariaResponseDTO {

    private final Integer id;
    private final LocalTime horaInicio;
    private final LocalTime horaFin;
}
