package org.cesde.academic.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.cesde.academic.enums.NombreDia;

@Getter
@Setter
@ToString
public class DiaRequestDTO {

    @NotNull(message = "El nombre del día es obligatorio")
    private NombreDia nombre;
}
