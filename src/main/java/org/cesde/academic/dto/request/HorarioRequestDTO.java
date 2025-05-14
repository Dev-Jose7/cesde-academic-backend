package org.cesde.academic.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HorarioRequestDTO {

    @NotNull(message = "El día es obligatorio")
    private Integer diaId;

    @NotNull(message = "La franja horaria es obligatoria")
    private Integer franjaId;
}
