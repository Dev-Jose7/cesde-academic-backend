package org.cesde.academic.dto.request;
import jakarta.validation.constraints.*;
import lombok.*;
import org.cesde.academic.enums.TipoModulo;

@Getter
@Setter
@ToString
public class ModuloRequestDTO {

    @NotNull
    private Integer programaId;

    @NotNull
    @Size(min = 1, max = 255, message = "El nombre del programa debe tener entre 1 y 255 caracteres")
    private String nombre;

    @NotNull
    private TipoModulo tipo;
}
