package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.model.FranjaHoraria;
import org.cesde.academic.service.IFranjaHorariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/franja")
public class FranjaHorariaController {

    @Autowired
    private IFranjaHorariaService franjaHorariaService;

    @PostMapping("/crear")
    public ResponseEntity<FranjaHoraria> createFranjaHoraria(@Valid @RequestBody FranjaHoraria franjaHoraria) {
        FranjaHoraria nuevaFranja = franjaHorariaService.createFranjaHoraria(franjaHoraria);
        return new ResponseEntity<>(nuevaFranja, HttpStatus.CREATED);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<FranjaHoraria>> getFranjaHorarias() {
        List<FranjaHoraria> lista = franjaHorariaService.getFranjaHorarias();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(lista, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFranjaHorariaById(@PathVariable Integer id) {
        Optional<FranjaHoraria> optionalFranja = franjaHorariaService.getFranjaHorariaById(id);

        return optionalFranja.map(franja -> new ResponseEntity<>(franja, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<FranjaHoraria> updateFranjaHoraria(@PathVariable Integer id, @Valid @RequestBody FranjaHoraria updatedFranja) {
        Optional<FranjaHoraria> optionalFranja = franjaHorariaService.getFranjaHorariaById(id);

        return optionalFranja
                .map(franja -> new ResponseEntity<>(franjaHorariaService.updateFranjaHoraria(franja, updatedFranja), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> deleteFranjaHoraria(@PathVariable Integer id) {
        Optional<FranjaHoraria> optionalFranja = franjaHorariaService.getFranjaHorariaById(id);

        if (optionalFranja.isPresent()) {
            franjaHorariaService.deleteFranjaHoraria(optionalFranja.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
