package org.cesde.academic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class AuthResponseDTO {
    private UsuarioResponseDTO usuario;
    private String mensaje;
    private String accessToken;
    private String refreshToken;
    private boolean status;
}
