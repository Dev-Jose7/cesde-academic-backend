package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.dto.request.ClaseRequestDTO;
import org.cesde.academic.dto.response.ClaseResponseDTO;
import org.cesde.academic.service.IClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clase")
public class ClaseController {

    @Autowired
    private IClaseService claseService;

    @PostMapping("/crear")
    public ResponseEntity<ClaseResponseDTO> createClase(@Valid @RequestBody ClaseRequestDTO request) {
        ClaseResponseDTO clase = claseService.createClase(request);
        return new ResponseEntity<>(clase, HttpStatus.CREATED);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<ClaseResponseDTO>> getClases() {
        List<ClaseResponseDTO> clases = claseService.getClases();
        return clases.isEmpty()
                ? new ResponseEntity<>(clases, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(clases, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClaseResponseDTO> getClaseById(@PathVariable Integer id) {
        ClaseResponseDTO clase = claseService.getClaseById(id);
        return new ResponseEntity<>(clase, HttpStatus.OK);
    }

    @GetMapping("/grupo/{grupoId}")
    public ResponseEntity<List<ClaseResponseDTO>> getClasesByGrupo(@PathVariable Integer grupoId) {
        List<ClaseResponseDTO> clases = claseService.getClasesByGrupo(grupoId);
        return clases.isEmpty()
                ? new ResponseEntity<>(clases, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(clases, HttpStatus.OK);
    }

    @GetMapping("/docente/{docenteId}")
    public ResponseEntity<List<ClaseResponseDTO>> getClasesByDocente(@PathVariable Integer docenteId) {
        List<ClaseResponseDTO> clases = claseService.getClasesByDocente(docenteId);
        return clases.isEmpty()
                ? new ResponseEntity<>(clases, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(clases, HttpStatus.OK);
    }

    @GetMapping("/modulo/{moduloId}")
    public ResponseEntity<List<ClaseResponseDTO>> getClasesByModulo(@PathVariable Integer moduloId) {
        List<ClaseResponseDTO> clases = claseService.getClasesByModulo(moduloId);
        return clases.isEmpty()
                ? new ResponseEntity<>(clases, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(clases, HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<ClaseResponseDTO> updateClase(@PathVariable Integer id, @Valid @RequestBody ClaseRequestDTO request) {
        ClaseResponseDTO clase = claseService.updateClase(id, request);
        return new ResponseEntity<>(clase, HttpStatus.OK);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> deleteClase(@PathVariable Integer id) {
        claseService.deleteClase(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
