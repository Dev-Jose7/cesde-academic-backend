package org.cesde.academic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.cesde.academic.enums.NombreRole;
import org.cesde.academic.model.Permission;

import java.util.Set;

@AllArgsConstructor
@Getter
@ToString
public class RoleResponseDTO {
    private Integer id;
    private NombreRole nombre;
    private Set<Permission> permisos;
}
