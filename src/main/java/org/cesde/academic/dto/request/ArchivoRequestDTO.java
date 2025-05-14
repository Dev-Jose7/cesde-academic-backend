package org.cesde.academic.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ArchivoRequestDTO {

    @NotNull(message = "El ID del usuario es obligatorio")
    private Integer usuarioId;

    @Size(max = 255, message = "El nombre del archivo no puede superar los 255 caracteres")
    private String nombreArchivo;

    @Size(max = 500, message = "La ruta del archivo no puede superar los 500 caracteres")
    private String rutaArchivo;

    @NotNull(message = "La fecha de subida es obligatoria")
    private LocalDate fechaSubida;
}
