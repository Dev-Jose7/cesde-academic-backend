package org.cesde.academic.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.cesde.academic.enums.TipoActividad;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ActividadRequestDTO {

    @NotNull(message = "El ID de la clase es obligatorio")
    private Integer claseId;

    @NotNull(message = "El título es obligatorio")
    @Size(min = 1, max = 100, message = "El título debe tener entre 1 y 100 caracteres")
    private String titulo;

    private String descripcion;

    @NotNull(message = "El tipo es obligatorio")
    private TipoActividad tipo;

    @NotNull(message = "La fecha de entrega es obligatoria")
    private LocalDateTime fechaEntrega;
}
