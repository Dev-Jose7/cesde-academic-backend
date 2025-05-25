package org.cesde.academic.service.impl;

import org.cesde.academic.dto.request.RoleRequestDTO;
import org.cesde.academic.dto.response.RoleResponseDTO;
import org.cesde.academic.enums.NombreRole;
import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.model.Role;
import org.cesde.academic.model.Permission;
import org.cesde.academic.repository.RoleRepository;
import org.cesde.academic.repository.PermissionRepository;
import org.cesde.academic.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public RoleResponseDTO createRole(RoleRequestDTO request) {
        Role role = createEntity(request);
        return createResponse(roleRepository.save(role));
    }

    @Override
    public List<RoleResponseDTO> getRoles() {
        return createResponseList(roleRepository.findAll());
    }

    @Override
    public RoleResponseDTO getRoleById(Integer id) {
        return createResponse(getRoleByIdOrException(id));
    }

    @Override
    public RoleResponseDTO getRoleByNombre(NombreRole nombre) {
        return createResponse(getRoleByNombreOrException(nombre));
    }

    @Override
    public void assignPermissionsToRole(Integer roleId, Set<Permission> permissions) {
        Role role = getRoleByIdOrException(roleId);
        role.getPermisos().addAll(permissions);
        roleRepository.save(role);
    }

    @Override
    public void deleteRole(Integer id) {
        Role role = getRoleByIdOrException(id);
        roleRepository.delete(role);
    }

    // ---------- Métodos Auxiliares ----------

    private Role getRoleByIdOrException(Integer id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Role no encontrado"));
    }

    private Role getRoleByNombreOrException(NombreRole nombre) {
        return roleRepository.findByNombre(nombre)
                .orElseThrow(() -> new RecursoNoEncontradoException("Role con nombre " + nombre + " no encontrado"));
    }

    private Role createEntity(RoleRequestDTO request) {
        Role role = new Role();
        role.setNombre(request.getNombre());
        role.setPermisos(request.getPermisos());  // Asignar permisos si los hay
        return role;
    }

    private RoleResponseDTO createResponse(Role role) {
        return new RoleResponseDTO(
                role.getId(),
                role.getNombre(),
                role.getPermisos()
        );
    }

    private List<RoleResponseDTO> createResponseList(List<Role> roles) {
        List<RoleResponseDTO> responseList = new ArrayList<>();
        for (Role role : roles) {
            responseList.add(createResponse(role));
        }
        return responseList;
    }
}
