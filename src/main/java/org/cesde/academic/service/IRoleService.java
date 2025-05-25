package org.cesde.academic.service;

import org.cesde.academic.dto.response.RoleResponseDTO;
import org.cesde.academic.dto.request.RoleRequestDTO;
import org.cesde.academic.model.Permission;
import org.cesde.academic.enums.NombreRole;

import java.util.List;
import java.util.Set;

public interface IRoleService {

    // Crear un nuevo Role
    RoleResponseDTO createRole(RoleRequestDTO roleRequestDTO);

    // Obtener un Role por su id
    RoleResponseDTO getRoleById(Integer id);

    // Obtener todos los Roles
    List<RoleResponseDTO> getRoles();

    // Obtener un Role por su nombre (enum)
    RoleResponseDTO getRoleByNombre(NombreRole nombre);

    // Asignar permisos a un Role
    void assignPermissionsToRole(Integer roleId, Set<Permission> permissions);

    // Eliminar un Role
    void deleteRole(Integer id);
}
