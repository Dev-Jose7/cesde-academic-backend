package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.model.ClaseHorario;
import org.cesde.academic.model.ClaseHorarioId;
import org.cesde.academic.service.IClaseHorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clase-horario")
public class ClaseHorarioController {

    @Autowired
    private IClaseHorarioService claseHorarioService;

    // Se debe crear el objeto ClaseHorarioId con claseId y horarioId para generar la clave primaria compuesta.
    // De esta manera poder realizar operaciones (get, put, delete).

    @PostMapping("/crear")
    public ResponseEntity<ClaseHorario> createClaseHorario(@Valid @RequestBody ClaseHorario claseHorario) {
        ClaseHorario nuevo = claseHorarioService.createClaseHorario(claseHorario);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<ClaseHorario>> getClaseHorarios() {
        List<ClaseHorario> lista = claseHorarioService.getClaseHorarios();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(lista, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }
    }

    @GetMapping("/{claseId}/{horarioId}")
    public ResponseEntity<?> getClaseHorarioById(@PathVariable Integer claseId, @PathVariable Integer horarioId) {
        ClaseHorarioId id = new ClaseHorarioId(claseId, horarioId);
        Optional<ClaseHorario> optional = claseHorarioService.getClaseHorarioById(id);
        return optional
                .map(ch -> new ResponseEntity<>(ch, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/clase/{id}")
    public ResponseEntity<List<ClaseHorario>> getByClase(@PathVariable Integer claseId) {
        List<ClaseHorario> lista = claseHorarioService.getClaseHorariosByClaseId(claseId);
        if (lista.isEmpty()) {
            return new ResponseEntity<>(lista, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }
    }

    @GetMapping("/horario/{id}")
    public ResponseEntity<List<ClaseHorario>> getByHorario(@PathVariable Integer horarioId) {
        List<ClaseHorario> lista = claseHorarioService.getClaseHorariosByHorarioId(horarioId);
        if (lista.isEmpty()) {
            return new ResponseEntity<>(lista, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }
    }

    @PutMapping("/editar/{claseId}/{horarioId}")
    public ResponseEntity<ClaseHorario> updateClaseHorario(
            @PathVariable Integer claseId,
            @PathVariable Integer horarioId,
            @Valid @RequestBody ClaseHorario updatedClaseHorario) {

        ClaseHorarioId id = new ClaseHorarioId(claseId, horarioId);
        Optional<ClaseHorario> optional = claseHorarioService.getClaseHorarioById(id);

        return optional
                .map(ch -> new ResponseEntity<>(claseHorarioService.updateClaseHorario(ch, updatedClaseHorario), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/remover/{claseId}/{horarioId}")
    public ResponseEntity<Void> deleteClaseHorario(@PathVariable Integer claseId, @PathVariable Integer horarioId) {
        ClaseHorarioId id = new ClaseHorarioId(claseId, horarioId);
        Optional<ClaseHorario> optional = claseHorarioService.getClaseHorarioById(id);

        if (optional.isPresent()) {
            claseHorarioService.deleteClaseHorario(optional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
