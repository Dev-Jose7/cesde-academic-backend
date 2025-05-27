package org.cesde.academic.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.cesde.academic.enums.NombreRole;
import org.cesde.academic.model.Permission;

import java.util.Set;

@Getter
@Setter
@ToString
public class RoleRequestDTO {

    @NotNull(message = "El nombre no puede estar nulo")
    private NombreRole nombre;

    private Set<Permission> permisos;
}
