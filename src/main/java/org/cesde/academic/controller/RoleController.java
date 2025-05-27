package org.cesde.academic.controller;

import org.cesde.academic.dto.request.RoleRequestDTO;
import org.cesde.academic.dto.response.RoleResponseDTO;
import org.cesde.academic.enums.NombreRole;
import org.cesde.academic.model.Permission;
import org.cesde.academic.service.IRoleService;
import org.cesde.academic.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    // Crear un nuevo role
    @PostMapping("/crear")
    @PreAuthorize("hasRole('ROLE_DEV') and hasAuthority('CREATE')")
    public ResponseEntity<RoleResponseDTO> createRole(@RequestBody RoleRequestDTO request) {
        RoleResponseDTO response = roleService.createRole(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Obtener todos los roles
    @GetMapping("/lista")
    @PreAuthorize("hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<RoleResponseDTO>> getRoles() {
        List<RoleResponseDTO> responseList = roleService.getRoles();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    // Obtener un role por ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<RoleResponseDTO> getRoleById(@PathVariable Integer id) {
        RoleResponseDTO response = roleService.getRoleById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Obtener un role por nombre (enum)
    @GetMapping("/nombre/{nombre}")
    @PreAuthorize("hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<RoleResponseDTO> getRoleByNombre(@PathVariable NombreRole nombre) {
        RoleResponseDTO response = roleService.getRoleByNombre(nombre);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Asignar permisos a un role
    @PostMapping("/{roleId}/asignar-permisos")
    @PreAuthorize("hasRole('ROLE_DEV') and hasAuthority('UPDATE')")
    public ResponseEntity<Void> assignPermissionsToRole(@PathVariable Integer roleId, @RequestBody Set<Permission> permissions) {
        roleService.assignPermissionsToRole(roleId, permissions);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Eliminar un role
    @DeleteMapping("/remover/{id}")
    @PreAuthorize("hasRole('ROLE_DEV') and hasAuthority('DELETE')")
    public ResponseEntity<Void> deleteRole(@PathVariable Integer id) {
        roleService.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
