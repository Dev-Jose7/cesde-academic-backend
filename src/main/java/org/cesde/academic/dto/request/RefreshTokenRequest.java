package org.cesde.academic.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RefreshTokenRequest {

    @NotNull(message = "Debe digitar el refresh token")
    private String refreshToken;
}

