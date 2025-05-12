package org.cesde.academic.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@ToString
public class SemestreRequestDTO {
    @NotNull
    @Size(min = 1, max = 255, message = "El nombre del programa debe tener entre 1 y 255 caracteres")
    private String nombre;
}
