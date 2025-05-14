package org.cesde.academic.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class ArchivoResponseDTO {

    private final Integer id;
    private final String nombre;
    private final String nombreArchivo;
    private final String rutaArchivo;
    private final LocalDate fechaSubida;
    private final LocalDateTime creado;
    private final LocalDateTime actualizado;
}
