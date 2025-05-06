package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.model.Asistencia;
import org.cesde.academic.service.IAsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/asistencia")
public class AsistenciaController {

    @Autowired
    private IAsistenciaService asistenciaService;

    // Endpoint para crear una nueva asistencia
    @PostMapping("/crear")
    public ResponseEntity<Asistencia> createAsistencia(@Valid @RequestBody Asistencia asistencia) {
        Asistencia newAsistencia = asistenciaService.createAsistencia(asistencia);
        return new ResponseEntity<>(newAsistencia, HttpStatus.CREATED);
    }

    // Endpoint para obtener todas las asistencias
    @GetMapping("/lista")
    public ResponseEntity<List<Asistencia>> getListaAsistencias() {
        List<Asistencia> asistenciaList = asistenciaService.getAsistencias();

        if (asistenciaList.isEmpty()) {
            return new ResponseEntity<>(asistenciaList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(asistenciaList, HttpStatus.OK);
        }
    }

    // Endpoint para obtener una asistencia por su ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getAsistenciaById(@PathVariable Integer id) {
        Optional<Asistencia> optionalAsistencia = asistenciaService.getAsistenciaById(id);

        return optionalAsistencia
                .map(asistencia -> new ResponseEntity<>(asistencia, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para obtener las asistencias por ID de clase
    @GetMapping("/clase/{id}")
    public ResponseEntity<List<Asistencia>> getAsistenciasByClaseId(@PathVariable Integer claseId) {
        List<Asistencia> asistenciaList = asistenciaService.getAsistenciasByClaseId(claseId);

        if (asistenciaList.isEmpty()) {
            return new ResponseEntity<>(asistenciaList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(asistenciaList, HttpStatus.OK);
        }
    }

    // Endpoint para obtener las asistencias por ID de estudiante
    @GetMapping("/estudiante/{id}")
    public ResponseEntity<List<Asistencia>> getAsistenciasByEstudianteId(@PathVariable Integer estudianteId) {
        List<Asistencia> asistenciaList = asistenciaService.getAsistenciasByEstudianteId(estudianteId);

        if (asistenciaList.isEmpty()) {
            return new ResponseEntity<>(asistenciaList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(asistenciaList, HttpStatus.OK);
        }
    }

    // Endpoint para actualizar una asistencia
    @PutMapping("/editar/{id}")
    public ResponseEntity<Asistencia> updateAsistencia(@PathVariable Integer id, @Valid @RequestBody Asistencia updatedAsistencia) {
        Optional<Asistencia> optionalAsistencia = asistenciaService.getAsistenciaById(id);

        return optionalAsistencia
                .map(asistencia -> new ResponseEntity<>(asistenciaService.updateAsistencia(asistencia, updatedAsistencia), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para eliminar una asistencia
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Asistencia> deleteAsistencia(@PathVariable Integer id) {
        Optional<Asistencia> optionalAsistencia = asistenciaService.getAsistenciaById(id);

        if (optionalAsistencia.isPresent()) {
            asistenciaService.deleteAsistencia(optionalAsistencia.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
