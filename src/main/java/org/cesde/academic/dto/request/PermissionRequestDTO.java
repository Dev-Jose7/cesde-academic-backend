package org.cesde.academic.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PermissionRequestDTO {

    @NotNull(message = "El nombre no puede estar nulo")
    private String nombre;
}
