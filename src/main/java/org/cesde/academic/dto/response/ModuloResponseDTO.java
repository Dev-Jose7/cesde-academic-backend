package org.cesde.academic.dto.response;

import lombok.*;
import org.cesde.academic.enums.TipoModulo;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class ModuloResponseDTO {
    private final Integer programaId;
    private final String nombre;
    private final TipoModulo tipo;
    private final LocalDateTime creado;
    private final LocalDateTime actualizado;
}
