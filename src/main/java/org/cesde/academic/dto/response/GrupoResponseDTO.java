package org.cesde.academic.dto.response;

import lombok.*;
import org.cesde.academic.enums.EstadoGrupo;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class GrupoResponseDTO {

    private final Integer id;
    private final String codigo;
    private final String programa;
    private final String semestre;
    private final EstadoGrupo estado;
    private final LocalDateTime creado;
    private final LocalDateTime actualizado;
}
