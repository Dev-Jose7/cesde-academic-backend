package org.cesde.academic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ClaseHorarioResponseDTO {

    private final Integer claseId;
    private final String claseNombre;  // Nombre de la clase o alguna propiedad relevante de la clase
    private final Integer horarioId;
    private final String dia;  // Nombre del día, por ejemplo, "LUNES"
    private final String horaInicio;  // Hora de inicio de la franja horaria
    private final String horaFin;  // Hora de fin de la franja horaria
}
