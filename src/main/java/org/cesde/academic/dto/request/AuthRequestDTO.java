package org.cesde.academic.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthRequestDTO {

    @NotNull(message = "Debes digitar una cédula")
    private String cedula;

    @NotNull(message = "Debes digitar una contraseña")
    @Size(min = 6, max = 255, message = "La contraseña debe tener al menos 6 caracteres y máximo 255")
    private String contrasena;
}
