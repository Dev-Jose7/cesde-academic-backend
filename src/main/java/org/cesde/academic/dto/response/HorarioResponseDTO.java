package org.cesde.academic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.cesde.academic.enums.NombreDia;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Getter
@ToString
public class HorarioResponseDTO {

    private final Integer id;
    private final NombreDia dia; // El nombre del día (LUNES, MARTES, etc.)
    private final LocalTime horaInicio; // Hora de inicio de la franja horaria
    private final LocalTime horaFin; // Hora de fin de la franja horaria
}
