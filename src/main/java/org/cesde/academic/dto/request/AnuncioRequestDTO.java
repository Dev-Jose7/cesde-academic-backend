package org.cesde.academic.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class AnuncioRequestDTO {

    @NotNull(message = "El ID de la clase es obligatorio")
    private Integer claseId;

    @NotNull(message = "El título es obligatorio")
    @Size(min = 1, max = 255, message = "El título debe tener entre 1 y 255 caracteres")
    private String titulo;

    private String mensaje;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;
}
