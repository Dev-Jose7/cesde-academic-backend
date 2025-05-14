package org.cesde.academic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.cesde.academic.enums.NombreDia;
import org.cesde.academic.model.FranjaHoraria;

import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@Getter
@ToString
public class ClaseHorarioResponseDTO {

    private final String clase;  // Nombre de la clase o alguna propiedad relevante de la clase
    private final NombreDia dia;
    private final LocalTime horaInicio;
    private final LocalTime horaFin;
}
