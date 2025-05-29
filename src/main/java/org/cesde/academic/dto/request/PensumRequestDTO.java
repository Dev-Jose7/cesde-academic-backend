package org.cesde.academic.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.cesde.academic.enums.NivelPensum;

@Getter
@Setter
@ToString
public class PensumRequestDTO {

    @NotNull(message = "Debes digitar el id del programa")
    private Integer programaId;

    @NotNull(message = "Debes digitar el id del modulo")
    private Integer moduloId;

    @NotNull(message = "Debes indicar un nivel de los disponibles")
    private NivelPensum nivel;
}
