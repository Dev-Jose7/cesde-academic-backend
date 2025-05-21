package org.cesde.academic.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.cesde.academic.enums.EstadoDesempeno;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class DesempenoRequestDTO {

    @NotNull(message = "El estudiante no puede ser nulo")
    private Integer estudianteId;

    @NotNull(message = "El modulo no puede ser nulo")
    private Integer moduloId;

    @DecimalMin(value = "0.0", inclusive = true, message = "La nota mínima es 0.0")
    @DecimalMax(value = "5.0", inclusive = true, message = "La nota máxima es 5.0")
    @NotNull(message = "La calificación no puede ser nula")
    private BigDecimal calificacion;

    private EstadoDesempeno estado;
}
