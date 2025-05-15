package org.cesde.academic.dto.response;

import lombok.*;
import org.cesde.academic.enums.NivelModulo;
import org.cesde.academic.enums.TipoModulo;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class ModuloResponseDTO {
    private final Integer id;
    private final Integer programaId;
    private final String nombre;
    private final TipoModulo tipo;
    private final NivelModulo nivel;
    private final LocalDateTime creado;
    private final LocalDateTime actualizado;
}
