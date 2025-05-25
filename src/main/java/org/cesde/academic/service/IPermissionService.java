package org.cesde.academic.service;

import org.cesde.academic.dto.request.PermissionRequestDTO;
import org.cesde.academic.dto.response.PermissionResponseDTO;
import org.cesde.academic.model.Permission;

import java.util.List;

public interface IPermissionService {

    // Crear un nuevo permiso
    PermissionResponseDTO createPermission(PermissionRequestDTO request);

    // Obtener todos los permisos
    List<PermissionResponseDTO> getPermissions();

    // Obtener un permiso por su ID
    PermissionResponseDTO getPermissionById(Integer id);

    // Obtener un permiso por su nombre
    PermissionResponseDTO getPermissionByName(String nombre);

    // Eliminar un permiso
    void deletePermission(Integer id);
}
