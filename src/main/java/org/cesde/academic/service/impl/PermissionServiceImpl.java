package org.cesde.academic.service.impl;

import org.cesde.academic.dto.request.PermissionRequestDTO;
import org.cesde.academic.dto.response.PermissionResponseDTO;
import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.model.Permission;
import org.cesde.academic.repository.PermissionRepository;
import org.cesde.academic.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public PermissionResponseDTO createPermission(PermissionRequestDTO request) {
        Permission permission = createEntity(request);
        return createResponse(permissionRepository.save(permission));
    }

    @Override
    public List<PermissionResponseDTO> getPermissions() {
        return createResponseList(permissionRepository.findAll());
    }

    @Override
    public PermissionResponseDTO getPermissionById(Integer id) {
        return createResponse(getPermissionByIdOrException(id));
    }

    @Override
    public PermissionResponseDTO getPermissionByName(String nombre) {
        return createResponse(getPermissionByNameOrException(nombre));
    }

    @Override
    public void deletePermission(Integer id) {
        Permission permission = getPermissionByIdOrException(id);
        permissionRepository.delete(permission);
    }

    // ---------- Métodos Auxiliares ----------

    private Permission getPermissionByIdOrException(Integer id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Permission no encontrada"));
    }

    private Permission getPermissionByNameOrException(String nombre) {
        return permissionRepository.findByNombre(nombre)
                .orElseThrow(() -> new RecursoNoEncontradoException("Permission con nombre " + nombre + " no encontrada"));
    }

    private Permission createEntity(PermissionRequestDTO request) {
        Permission permission = new Permission();
        permission.setNombre(request.getNombre());
        return permission;
    }

    private PermissionResponseDTO createResponse(Permission permission) {
        return new PermissionResponseDTO(
                permission.getId(),
                permission.getNombre()
        );
    }

    private List<PermissionResponseDTO> createResponseList(List<Permission> permissions) {
        List<PermissionResponseDTO> responseList = new ArrayList<>();
        for (Permission permission : permissions) {
            responseList.add(createResponse(permission));
        }
        return responseList;
    }
}
