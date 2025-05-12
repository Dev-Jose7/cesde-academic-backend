package org.cesde.academic.dto.response;

import lombok.*;
import org.cesde.academic.enums.EstadoUsuario;
import org.cesde.academic.enums.TipoUsuario;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class UsuarioResponseDTO {

    private final Integer id;
    private final String nombre;
    private final TipoUsuario tipo;
    private final EstadoUsuario estado;
    private final LocalDateTime creado;
    private final LocalDateTime actualizado;
}
