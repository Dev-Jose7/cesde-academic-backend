package org.cesde.academic.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class ArchivoResponseDTO {

    private final Integer id;
    private final Integer claseId;
    private final Integer usuarioId;
    private final String nombreArchivo;
    private final String rutaArchivo;
    private final LocalDateTime fechaSubida;
    private final LocalDateTime creado;
    private final LocalDateTime actualizado;
}
