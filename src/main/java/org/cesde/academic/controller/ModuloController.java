package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.dto.request.ModuloRequestDTO;
import org.cesde.academic.dto.response.ModuloResponseDTO;
import org.cesde.academic.service.IModuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modulo")
public class ModuloController {

    @Autowired
    private IModuloService moduloService;

    // Crear un nuevo módulo
    @PostMapping("/crear")
    @PreAuthorize("hasRole('ROLE_DIRECTIVO') or hasRole('ROLE_DEV') and hasAuthority('CREATE')")
    public ResponseEntity<ModuloResponseDTO> createModulo(@Valid @RequestBody ModuloRequestDTO request) {
        ModuloResponseDTO newModulo = moduloService.createModulo(request);
        return new ResponseEntity<>(newModulo, HttpStatus.CREATED);
    }

    // Obtener todos los módulos
    @GetMapping("/lista")
    @PreAuthorize("hasRole('ROLE_DIRECTIVO') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<ModuloResponseDTO>> getListaModulos() {
        List<ModuloResponseDTO> moduloList = moduloService.getModulos();

        if (moduloList.isEmpty()) {
            return new ResponseEntity<>(moduloList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(moduloList, HttpStatus.OK);
        }
    }

    // Obtener módulo por ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_DIRECTIVO') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<ModuloResponseDTO> getModuloById(@PathVariable Integer id) {
        ModuloResponseDTO modulo = moduloService.getModuloById(id);
        return new ResponseEntity<>(modulo, HttpStatus.OK);
    }

    // Buscar módulos por nombre
    @GetMapping("/buscar/{nombre}")
    @PreAuthorize("hasRole('ROLE_DIRECTIVO') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<ModuloResponseDTO>> getModuloByNombre(@PathVariable String nombre) {
        List<ModuloResponseDTO> moduloList = moduloService.getModuloByNombre(nombre);

        if (moduloList.isEmpty()) {
            return new ResponseEntity<>(moduloList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(moduloList, HttpStatus.OK);
        }
    }

    // Actualizar módulo
    @PutMapping("/editar/{id}")
        @PreAuthorize("hasRole('ROLE_DIRECTIVO') or hasRole('ROLE_DEV') and hasAuthority('UPDATE')")
    public ResponseEntity<ModuloResponseDTO> updateModulo(@PathVariable Integer id, @Valid @RequestBody ModuloRequestDTO request) {
        ModuloResponseDTO updatedModulo = moduloService.updateModulo(id, request);
        return new ResponseEntity<>(updatedModulo, HttpStatus.OK);
    }

    // Eliminar módulo
    @DeleteMapping("/remover/{id}")
    @PreAuthorize("hasRole('ROLE_DEV') and hasAuthority('DELETE')")
    public ResponseEntity<Void> deleteModulo(@PathVariable Integer id) {
        moduloService.deleteModulo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
