package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.model.Clase;
import org.cesde.academic.service.IClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clase")
public class ClaseController {

    @Autowired
    private IClaseService claseService;

    // Endpoint para crear una nueva clase
    @PostMapping("/crear")
    public ResponseEntity<Clase> createClase(@Valid @RequestBody Clase clase) {
        Clase newClase = claseService.createClase(clase);
        return new ResponseEntity<>(newClase, HttpStatus.CREATED);
    }

    // Endpoint para obtener todas las clases
    @GetMapping("/lista")
    public ResponseEntity<List<Clase>> getListaClases() {
        List<Clase> claseList = claseService.getClases();

        if (claseList.isEmpty()) {
            return new ResponseEntity<>(claseList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(claseList, HttpStatus.OK);
        }
    }

    // Endpoint para obtener una clase por su ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getClaseById(@PathVariable Integer id) {
        Optional<Clase> optionalClase = claseService.getClaseById(id);

        return optionalClase
                .map(clase -> new ResponseEntity<>(clase, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para obtener las clases de un grupo
    @GetMapping("/grupo/{id}")
    public ResponseEntity<List<Clase>> getClasesByGrupoId(@PathVariable Integer grupoId) {
        List<Clase> claseList = claseService.getClasesByGrupoId(grupoId);

        if (claseList.isEmpty()) {
            return new ResponseEntity<>(claseList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(claseList, HttpStatus.OK);
        }
    }

    // Endpoint para obtener las clases de un docente
    @GetMapping("/docente/{id}")
    public ResponseEntity<List<Clase>> getClasesByDocenteId(@PathVariable Integer docenteId) {
        List<Clase> claseList = claseService.getClasesByDocenteId(docenteId);

        if (claseList.isEmpty()) {
            return new ResponseEntity<>(claseList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(claseList, HttpStatus.OK);
        }
    }

    // Endpoint para obtener las clases de un módulo
    @GetMapping("/modulo/{id}")
    public ResponseEntity<List<Clase>> getClasesByModuloId(@PathVariable Integer moduloId) {
        List<Clase> claseList = claseService.getClasesByModuloId(moduloId);

        if (claseList.isEmpty()) {
            return new ResponseEntity<>(claseList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(claseList, HttpStatus.OK);
        }
    }

    // Endpoint para actualizar una clase
    @PutMapping("/editar/{id}")
    public ResponseEntity<Clase> updateClase(@PathVariable Integer id, @Valid @RequestBody Clase updatedClase) {
        Optional<Clase> optionalClase = claseService.getClaseById(id);

        return optionalClase
                .map(clase -> new ResponseEntity<>(claseService.updateClase(clase, updatedClase), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para eliminar una clase
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Clase> deleteClase(@PathVariable Integer id) {
        Optional<Clase> optionalClase = claseService.getClaseById(id);

        if (optionalClase.isPresent()) {
            claseService.deleteClase(optionalClase.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
