package org.cesde.academic.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.cesde.academic.enums.EstadoGrupo;

@Getter
@Setter
@ToString
public class GrupoRequestDTO {

    @NotNull(message = "El programa es obligatorio")
    private Integer programaId;

    @NotNull(message = "El semestre es obligatorio")
    private Integer semestreId;

    private EstadoGrupo estado;
}
