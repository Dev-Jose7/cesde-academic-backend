package org.cesde.academic.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import org.cesde.academic.enums.EstadoUsuario;
import org.cesde.academic.enums.TipoUsuario;

@Getter
@Setter
@ToString
public class UsuarioRequestDTO {

    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 1, max = 255, message = "El nombre debe tener entre 1 y 255 caracteres")
    private String nombre;

    @NotNull(message = "El correo no puede ser nulo")
    @Email(message = "El correo debe ser válido")
    @Size(max = 255, message = "El correo no puede exceder los 255 caracteres")
    private String correo;

    @NotNull(message = "La contraseña no puede ser nula")
    @Size(min = 6, max = 255, message = "La contraseña debe tener al menos 6 caracteres y máximo 255")
    private String contrasena;

    @NotNull(message = "El tipo de usuario no puede ser nulo")
    private TipoUsuario tipo;

    @NotNull(message = "El estado del usuario no puede ser nulo")
    private EstadoUsuario estado;
}
