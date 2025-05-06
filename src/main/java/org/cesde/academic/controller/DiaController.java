package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.model.Dia;
import org.cesde.academic.service.IDiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dia")
public class DiaController {

    @Autowired
    private IDiaService diaService;

    // Endpoint para crear un nuevo día
    @PostMapping("/crear")
    public ResponseEntity<Dia> createDia(@Valid @RequestBody Dia dia) {
        Dia newDia = diaService.createDia(dia);
        return new ResponseEntity<>(newDia, HttpStatus.CREATED);
    }

    // Endpoint para obtener todos los días
    @GetMapping("/lista")
    public ResponseEntity<List<Dia>> getListaDias() {
        List<Dia> diaList = diaService.getDias();

        if (diaList.isEmpty()) {
            return new ResponseEntity<>(diaList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(diaList, HttpStatus.OK);
        }
    }

    // Endpoint para obtener un día por su ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getDiaById(@PathVariable Integer id) {
        Optional<Dia> optionalDia = diaService.getDiaById(id);

        return optionalDia
                .map(dia -> new ResponseEntity<>(dia, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para actualizar un día
    @PutMapping("/editar/{id}")
    public ResponseEntity<Dia> updateDia(@PathVariable Integer id, @Valid @RequestBody Dia updatedDia) {
        Optional<Dia> optionalDia = diaService.getDiaById(id);

        return optionalDia
                .map(dia -> new ResponseEntity<>(diaService.updateDia(dia, updatedDia), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para eliminar un día
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Dia> deleteDia(@PathVariable Integer id) {
        Optional<Dia> optionalDia = diaService.getDiaById(id);

        if (optionalDia.isPresent()) {
            diaService.deleteDia(optionalDia.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
