package org.cesde.academic.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class CalificacionRequestDTO {

    @NotNull(message = "El ID de la actividad es obligatorio")
    private Integer actividadId;

    @NotNull(message = "El ID del estudiante es obligatorio")
    private Integer estudianteId;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    @DecimalMin(value = "0.0", inclusive = true, message = "La nota mínima es 0.0")
    @DecimalMax(value = "5.0", inclusive = true, message = "La nota máxima es 5.0")
    private BigDecimal nota;
}
