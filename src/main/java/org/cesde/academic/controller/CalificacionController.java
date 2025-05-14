package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.dto.request.CalificacionRequestDTO;
import org.cesde.academic.dto.response.CalificacionResponseDTO;
import org.cesde.academic.service.ICalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calificacion")
public class CalificacionController {

    @Autowired
    private ICalificacionService calificacionService;

    @PostMapping("/crear")
    public ResponseEntity<CalificacionResponseDTO> createCalificacion(@Valid @RequestBody CalificacionRequestDTO request) {
        CalificacionResponseDTO calificacion = calificacionService.createCalificacion(request);
        return new ResponseEntity<>(calificacion, HttpStatus.CREATED);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<CalificacionResponseDTO>> getCalificaciones() {
        List<CalificacionResponseDTO> calificaciones = calificacionService.getCalificaciones();
        return calificaciones.isEmpty()
                ? new ResponseEntity<>(calificaciones, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(calificaciones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalificacionResponseDTO> getCalificacionById(@PathVariable Integer id) {
        CalificacionResponseDTO calificacion = calificacionService.getCalificacionById(id);
        return new ResponseEntity<>(calificacion, HttpStatus.OK);
    }

    @GetMapping("/actividad/{actividadId}")
    public ResponseEntity<List<CalificacionResponseDTO>> getCalificacionesByActividad(@PathVariable Integer actividadId) {
        List<CalificacionResponseDTO> calificaciones = calificacionService.getCalificacionesByActividad(actividadId);
        return calificaciones.isEmpty()
                ? new ResponseEntity<>(calificaciones, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(calificaciones, HttpStatus.OK);
    }

    @GetMapping("/estudiante/{estudianteId}")
    public ResponseEntity<List<CalificacionResponseDTO>> getCalificacionesByEstudiante(@PathVariable Integer estudianteId) {
        List<CalificacionResponseDTO> calificaciones = calificacionService.getCalificacionesByEstudiante(estudianteId);
        return calificaciones.isEmpty()
                ? new ResponseEntity<>(calificaciones, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(calificaciones, HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<CalificacionResponseDTO> updateCalificacion(@PathVariable Integer id, @Valid @RequestBody CalificacionRequestDTO request) {
        CalificacionResponseDTO calificacion = calificacionService.updateCalificacion(id, request);
        return new ResponseEntity<>(calificacion, HttpStatus.OK);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> deleteCalificacion(@PathVariable Integer id) {
        calificacionService.deleteCalificacion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
