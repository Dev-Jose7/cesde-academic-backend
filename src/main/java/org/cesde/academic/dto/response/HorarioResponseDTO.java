package org.cesde.academic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class HorarioResponseDTO {

    private final Integer id;
    private final String dia; // El nombre del día (LUNES, MARTES, etc.)
    private final String horaInicio; // Hora de inicio de la franja horaria
    private final String horaFin; // Hora de fin de la franja horaria
}
