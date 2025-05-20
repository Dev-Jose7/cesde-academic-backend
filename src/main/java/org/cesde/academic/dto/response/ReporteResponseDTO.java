package org.cesde.academic.dto.response;

import lombok.*;
import org.cesde.academic.enums.EstadoReporte;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class ReporteResponseDTO {

    private final Integer id;
    private final ClaseResponseInfoDTO clase;
    private final String usuario;
    private final String titulo;
    private final String descripcion;
    private final LocalDate fecha;
    private final EstadoReporte estado;
    private final LocalDateTime creado;
    private final LocalDateTime actualizado;
}
