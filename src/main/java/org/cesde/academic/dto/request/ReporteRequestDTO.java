package org.cesde.academic.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.cesde.academic.enums.EstadoReporte;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class ReporteRequestDTO {

    @NotNull(message = "El ID de la clase es obligatorio")
    private Integer claseId;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Integer usuarioId;

    @NotNull(message = "La descripción es obligatoria")
    private String descripcion;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    @NotNull(message = "El estado es obligatorio")
    @Size(max = 50, message = "El estado no puede exceder los 50 caracteres")
    private EstadoReporte estado;
}
