package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.model.Actividad;
import org.cesde.academic.service.IActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/actividad")
public class ActividadController {

    @Autowired
    private IActividadService actividadService;

    // Endpoint para crear una nueva actividad
    @PostMapping("/crear")
    public ResponseEntity<Actividad> createActividad(@Valid @RequestBody Actividad actividad) {
        Actividad newActividad = actividadService.createActividad(actividad);
        return new ResponseEntity<>(newActividad, HttpStatus.CREATED);
    }

    // Endpoint para obtener todas las actividades
    @GetMapping("/lista")
    public ResponseEntity<List<Actividad>> getListaActividades() {
        List<Actividad> actividadList = actividadService.getActividades();

        if (actividadList.isEmpty()) {
            return new ResponseEntity<>(actividadList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(actividadList, HttpStatus.OK);
        }
    }

    // Endpoint para obtener una actividad por su ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getActividadById(@PathVariable Integer id) {
        Optional<Actividad> optionalActividad = actividadService.getActividadById(id);

        return optionalActividad
                .map(actividad -> new ResponseEntity<>(actividad, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para obtener las actividades de una clase
    @GetMapping("/clase/{claseId}")
    public ResponseEntity<List<Actividad>> getActividadesByClaseId(@PathVariable Integer claseId) {
        List<Actividad> actividadList = actividadService.getActividadesByClaseId(claseId);

        if (actividadList.isEmpty()) {
            return new ResponseEntity<>(actividadList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(actividadList, HttpStatus.OK);
        }
    }

    // Endpoint para actualizar una actividad
    @PutMapping("/edit/{id}")
    public ResponseEntity<Actividad> updateActividad(@PathVariable Integer id, @Valid @RequestBody Actividad updatedActividad) {
        Optional<Actividad> optionalActividad = actividadService.getActividadById(id);

        return optionalActividad
                .map(actividad -> new ResponseEntity<>(actividadService.updateActividad(actividad, updatedActividad), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para eliminar una actividad
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Actividad> deleteActividad(@PathVariable Integer id) {
        Optional<Actividad> optionalActividad = actividadService.getActividadById(id);

        if (optionalActividad.isPresent()) {
            actividadService.deleteActividad(optionalActividad.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
