package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.model.Horario;
import org.cesde.academic.service.IHorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/horario")
public class HorarioController {

    @Autowired
    private IHorarioService horarioService;

    @PostMapping("/crear")
    public ResponseEntity<Horario> createHorario(@Valid @RequestBody Horario horario) {
        Horario nuevoHorario = horarioService.createHorario(horario);
        return new ResponseEntity<>(nuevoHorario, HttpStatus.CREATED);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Horario>> getHorarios() {
        List<Horario> lista = horarioService.getHorarios();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(lista, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getHorarioById(@PathVariable Integer id) {
        Optional<Horario> optionalHorario = horarioService.getHorarioById(id);
        return optionalHorario
                .map(horario -> new ResponseEntity<>(horario, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/dia/{id}")
    public ResponseEntity<List<Horario>> getHorariosByDia(@PathVariable Integer diaId) {
        List<Horario> lista = horarioService.getHorariosByDiaId(diaId);
        if (lista.isEmpty()) {
            return new ResponseEntity<>(lista, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }
    }

    @GetMapping("/franja/{id}")
    public ResponseEntity<List<Horario>> getHorariosByFranja(@PathVariable Integer franjaId) {
        List<Horario> lista = horarioService.getHorariosByFranjaId(franjaId);
        if (lista.isEmpty()) {
            return new ResponseEntity<>(lista, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Horario> updateHorario(@PathVariable Integer id, @Valid @RequestBody Horario updatedHorario) {
        Optional<Horario> optionalHorario = horarioService.getHorarioById(id);
        return optionalHorario
                .map(horario -> new ResponseEntity<>(horarioService.updateHorario(horario, updatedHorario), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> deleteHorario(@PathVariable Integer id) {
        Optional<Horario> optionalHorario = horarioService.getHorarioById(id);
        if (optionalHorario.isPresent()) {
            horarioService.deleteHorario(optionalHorario.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
