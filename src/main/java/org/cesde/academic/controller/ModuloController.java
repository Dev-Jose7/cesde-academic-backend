package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.dto.request.ModuloRequestDTO;
import org.cesde.academic.dto.response.ModuloResponseDTO;
import org.cesde.academic.service.IModuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modulo")
public class ModuloController {

    @Autowired
    private IModuloService moduloService;

    // Crear un nuevo módulo
    @PostMapping("/crear")
    public ResponseEntity<ModuloResponseDTO> createModulo(@Valid @RequestBody ModuloRequestDTO request) {
        ModuloResponseDTO newModulo = moduloService.createModulo(request);
        return new ResponseEntity<>(newModulo, HttpStatus.CREATED);
    }

    // Obtener todos los módulos
    @GetMapping("/lista")
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
    public ResponseEntity<ModuloResponseDTO> getModuloById(@PathVariable Integer id) {
        ModuloResponseDTO modulo = moduloService.getModuloById(id);
        return new ResponseEntity<>(modulo, HttpStatus.OK);
    }

    // Buscar módulos por nombre
    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<ModuloResponseDTO>> getModuloByNombre(@PathVariable String nombre) {
        List<ModuloResponseDTO> moduloList = moduloService.getModuloByNombre(nombre);

        if (moduloList.isEmpty()) {
            return new ResponseEntity<>(moduloList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(moduloList, HttpStatus.OK);
        }
    }

    // Obtener módulos por programa
    @GetMapping("/programa/{id}")
    public ResponseEntity<List<ModuloResponseDTO>> getModulosByProgramaId(@PathVariable Integer id) {
        List<ModuloResponseDTO> moduloList = moduloService.getModulosByProgramaId(id);

        if (moduloList.isEmpty()) {
            return new ResponseEntity<>(moduloList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(moduloList, HttpStatus.OK);
        }
    }

    // Actualizar módulo
    @PutMapping("/editar/{id}")
    public ResponseEntity<ModuloResponseDTO> updateModulo(@PathVariable Integer id, @Valid @RequestBody ModuloRequestDTO request) {
        ModuloResponseDTO updatedModulo = moduloService.updateModulo(id, request);
        return new ResponseEntity<>(updatedModulo, HttpStatus.OK);
    }

    // Eliminar módulo
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> deleteModulo(@PathVariable Integer id) {
        moduloService.deleteModulo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
