package org.cesde.academic.controller;

import org.cesde.academic.dto.request.PermissionRequestDTO;
import org.cesde.academic.dto.response.PermissionResponseDTO;
import org.cesde.academic.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    // Crear un nuevo permiso
    @PostMapping("/crear")
    @PreAuthorize("hasRole('ROLE_DEV') and hasAuthority('CREATE')")
    public ResponseEntity<PermissionResponseDTO> createPermission(@RequestBody PermissionRequestDTO request) {
        PermissionResponseDTO response = permissionService.createPermission(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Obtener todos los permisos
    @GetMapping("/lista")
    @PreAuthorize("hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<PermissionResponseDTO>> getPermissions() {
        List<PermissionResponseDTO> responseList = permissionService.getPermissions();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    // Obtener un permiso por ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<PermissionResponseDTO> getPermissionById(@PathVariable Integer id) {
        PermissionResponseDTO response = permissionService.getPermissionById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Obtener un permiso por nombre
    @GetMapping("/nombre/{nombre}")
    @PreAuthorize("hasRole('ROLE_DEV') and hasAuthority('UPDATE')")
    public ResponseEntity<PermissionResponseDTO> getPermissionByName(@PathVariable String nombre) {
        PermissionResponseDTO response = permissionService.getPermissionByName(nombre);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Eliminar un permiso
    @DeleteMapping("/remover/{id}")
    @PreAuthorize("hasRole('ROLE_DEV') and hasAuthority('DELETE')")
    public ResponseEntity<Void> deletePermission(@PathVariable Integer id) {
        permissionService.deletePermission(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
