package org.cesde.academic.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class ReporteResponseDTO {

    private final Integer id;
    private final Integer claseId;
    private final Integer usuarioId;
    private final String descripcion;
    private final LocalDateTime fecha;
    private final String estado;
    private final LocalDateTime creado;
    private final LocalDateTime actualizado;
}
