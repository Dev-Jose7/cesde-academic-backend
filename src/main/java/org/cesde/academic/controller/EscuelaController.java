package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.model.Escuela;
import org.cesde.academic.service.IEscuelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/escuela")
public class EscuelaController {

    @Autowired
    IEscuelaService escuelaService;

    @PostMapping("/crear")
    public ResponseEntity<Escuela> createEscuela(@Valid @RequestBody Escuela escuela){
        Escuela newEscuela = escuelaService.createEscuela(escuela);
        return new ResponseEntity<>(newEscuela, HttpStatus.CREATED);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Escuela>> getListaEscuela(){
        List<Escuela> escuelaList = escuelaService.getEscuelas();

        if(escuelaList.isEmpty()){
            return new ResponseEntity<>(escuelaList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(escuelaList, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEscuelaById(@PathVariable Integer id){
        Optional<Escuela> optionalEscuela = escuelaService.getEscuelaById(id);

        return optionalEscuela.map(escuela -> new ResponseEntity<>(escuela, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Escuela> updateEscuela(@PathVariable Integer id, @Valid @RequestBody Escuela updatedEscuela){
        Optional<Escuela> optionalEscuela = escuelaService.getEscuelaById(id);

        return optionalEscuela
                .map(escuela -> new ResponseEntity<>(escuelaService.updateEscuela(escuela, updatedEscuela), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Escuela> deleteEscuela(@PathVariable Integer id){
        Optional<Escuela> optionalEscuela = escuelaService.getEscuelaById(id);

        if(optionalEscuela.isPresent()){
            escuelaService.deleteEscuela(optionalEscuela.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
