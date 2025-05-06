package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.model.Calificacion;
import org.cesde.academic.service.ICalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/calificacion")
public class CalificacionController {

    @Autowired
    private ICalificacionService calificacionService;

    // Endpoint para crear una nueva calificación
    @PostMapping("/crear")
    public ResponseEntity<Calificacion> createCalificacion(@Valid @RequestBody Calificacion calificacion) {
        Calificacion newCalificacion = calificacionService.createCalificacion(calificacion);
        return new ResponseEntity<>(newCalificacion, HttpStatus.CREATED);
    }

    // Endpoint para obtener todas las calificaciones
    @GetMapping("/lista")
    public ResponseEntity<List<Calificacion>> getListaCalificaciones() {
        List<Calificacion> calificacionList = calificacionService.getCalificaciones();

        if (calificacionList.isEmpty()) {
            return new ResponseEntity<>(calificacionList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(calificacionList, HttpStatus.OK);
        }
    }

    // Endpoint para obtener una calificación por su ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCalificacionById(@PathVariable Integer id) {
        Optional<Calificacion> optionalCalificacion = calificacionService.getCalificacionById(id);

        return optionalCalificacion
                .map(calificacion -> new ResponseEntity<>(calificacion, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para obtener las calificaciones de una actividad
    @GetMapping("/actividad/{actividadId}")
    public ResponseEntity<List<Calificacion>> getCalificacionesByActividadId(@PathVariable Integer actividadId) {
        List<Calificacion> calificacionList = calificacionService.getCalificacionesByActividadId(actividadId);

        if (calificacionList.isEmpty()) {
            return new ResponseEntity<>(calificacionList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(calificacionList, HttpStatus.OK);
        }
    }

    // Endpoint para obtener las calificaciones de un estudiante
    @GetMapping("/estudiante/{estudianteId}")
    public ResponseEntity<List<Calificacion>> getCalificacionesByEstudianteId(@PathVariable Integer estudianteId) {
        List<Calificacion> calificacionList = calificacionService.getCalificacionesByEstudianteId(estudianteId);

        if (calificacionList.isEmpty()) {
            return new ResponseEntity<>(calificacionList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(calificacionList, HttpStatus.OK);
        }
    }

    // Endpoint para actualizar una calificación
    @PutMapping("/edit/{id}")
    public ResponseEntity<Calificacion> updateCalificacion(@PathVariable Integer id, @Valid @RequestBody Calificacion updatedCalificacion) {
        Optional<Calificacion> optionalCalificacion = calificacionService.getCalificacionById(id);

        return optionalCalificacion
                .map(calificacion -> new ResponseEntity<>(calificacionService.updateCalificacion(calificacion, updatedCalificacion), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para eliminar una calificación
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Calificacion> deleteCalificacion(@PathVariable Integer id) {
        Optional<Calificacion> optionalCalificacion = calificacionService.getCalificacionById(id);

        if (optionalCalificacion.isPresent()) {
            calificacionService.deleteCalificacion(optionalCalificacion.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
