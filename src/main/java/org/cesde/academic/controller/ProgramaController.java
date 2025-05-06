package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.model.Programa;
import org.cesde.academic.service.IProgramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/programa")
public class ProgramaController {

    @Autowired
    IProgramaService programaService;

    // Endpoint para crear un nuevo programa
    @PostMapping("/crear")
    public ResponseEntity<Programa> createPrograma(@Valid @RequestBody Programa programa){
        Programa newPrograma = programaService.createPrograma(programa);
        return new ResponseEntity<>(newPrograma, HttpStatus.CREATED);
    }

    // Endpoint para obtener la lista de programas
    @GetMapping("/lista")
    public ResponseEntity<List<Programa>> getListaProgramas(){
        List<Programa> programaList = programaService.getProgramas();

        if(programaList.isEmpty()){
            return new ResponseEntity<>(programaList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(programaList, HttpStatus.OK);
        }
    }

    // Endpoint para obtener un programa por su ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getProgramaById(@PathVariable Integer id){
        Optional<Programa> optionalPrograma = programaService.getProgramaById(id);

        return optionalPrograma.map(programa -> new ResponseEntity<>(programa, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para actualizar un programa
    @PutMapping("/editar/{id}")
    public ResponseEntity<Programa> updatePrograma(@PathVariable Integer id, @Valid @RequestBody Programa updatedPrograma){
        Optional<Programa> optionalPrograma = programaService.getProgramaById(id);

        return optionalPrograma
                .map(programa -> new ResponseEntity<>(programaService.updatePrograma(programa, updatedPrograma), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para eliminar un programa
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Programa> deletePrograma(@PathVariable Integer id){
        Optional<Programa> optionalPrograma = programaService.getProgramaById(id);

        if(optionalPrograma.isPresent()){
            programaService.deletePrograma(optionalPrograma.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para obtener programas de una escuela específica
    @GetMapping("/escuela/{id}")
    public ResponseEntity<List<Programa>> getProgramasByEscuelaId(@PathVariable Integer id) {
        List<Programa> programaList = programaService.getProgramasByEscuelaId(id);

        if (programaList.isEmpty()) {
            return new ResponseEntity<>(programaList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(programaList, HttpStatus.OK);
        }
    }
}
