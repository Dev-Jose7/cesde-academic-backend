package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.model.Semestre;
import org.cesde.academic.service.ISemestreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/semestre")
public class SemestreController {

    @Autowired
    ISemestreService semestreService;

    // Endpoint para crear un nuevo semestre
    @PostMapping("/crear")
    public ResponseEntity<Semestre> createSemestre(@Valid @RequestBody Semestre semestre){
        Semestre newSemestre = semestreService.createSemestre(semestre);
        return new ResponseEntity<>(newSemestre, HttpStatus.CREATED);
    }

    // Endpoint para obtener la lista de semestres
    @GetMapping("/lista")
    public ResponseEntity<List<Semestre>> getListaSemestres(){
        List<Semestre> semestreList = semestreService.getSemestres();

        if(semestreList.isEmpty()){
            return new ResponseEntity<>(semestreList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(semestreList, HttpStatus.OK);
        }
    }

    // Endpoint para obtener un semestre por su ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getSemestreById(@PathVariable Integer id){
        Optional<Semestre> optionalSemestre = semestreService.getSemestreById(id);

        return optionalSemestre.map(semestre -> new ResponseEntity<>(semestre, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para actualizar un semestre
    @PutMapping("/editar/{id}")
    public ResponseEntity<Semestre> updateSemestre(@PathVariable Integer id, @Valid @RequestBody Semestre updatedSemestre){
        Optional<Semestre> optionalSemestre = semestreService.getSemestreById(id);

        return optionalSemestre
                .map(semestre -> new ResponseEntity<>(semestreService.updateSemestre(semestre, updatedSemestre), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para eliminar un semestre
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Semestre> deleteSemestre(@PathVariable Integer id){
        Optional<Semestre> optionalSemestre = semestreService.getSemestreById(id);

        if(optionalSemestre.isPresent()){
            semestreService.deleteSemestre(optionalSemestre.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
