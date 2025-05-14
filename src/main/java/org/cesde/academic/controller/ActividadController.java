package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.dto.request.ActividadRequestDTO;
import org.cesde.academic.dto.response.ActividadResponseDTO;
import org.cesde.academic.enums.TipoActividad;
import org.cesde.academic.service.IActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actividad")
public class ActividadController {

    @Autowired
    private IActividadService actividadService;

    @PostMapping("/crear")
    public ResponseEntity<ActividadResponseDTO> createActividad(@Valid @RequestBody ActividadRequestDTO request) {
        ActividadResponseDTO actividad = actividadService.createActividad(request);
        return new ResponseEntity<>(actividad, HttpStatus.CREATED);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<ActividadResponseDTO>> getActividades() {
        List<ActividadResponseDTO> actividades = actividadService.getActividades();
        return actividades.isEmpty()
                ? new ResponseEntity<>(actividades, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(actividades, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActividadResponseDTO> getActividadById(@PathVariable Integer id) {
        ActividadResponseDTO actividad = actividadService.getActividadById(id);
        return new ResponseEntity<>(actividad, HttpStatus.OK);
    }

    @GetMapping("/clase/{id}")
    public ResponseEntity<List<ActividadResponseDTO>> getActividadesByClase(@PathVariable Integer claseId) {
        List<ActividadResponseDTO> actividades = actividadService.getActividadesByClase(claseId);
        return actividades.isEmpty()
                ? new ResponseEntity<>(actividades, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(actividades, HttpStatus.OK);
    }

    @GetMapping("/clase/buscar/titulo/{titulo}")
    public ResponseEntity<List<ActividadResponseDTO>> getActividadesByTitulo(@PathVariable String titulo){
        List<ActividadResponseDTO> actividades = actividadService.getActividadesByTitulo(titulo);
        return actividades.isEmpty()
                ? new ResponseEntity<>(actividades, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(actividades, HttpStatus.OK);
    }

    @GetMapping("/clase/buscar/tipo/{tipo}")
    public ResponseEntity<List<ActividadResponseDTO>> getActividadesByTipo(@PathVariable TipoActividad tipo){
        List<ActividadResponseDTO> actividades = actividadService.getActividadesByTipo(tipo);
        return actividades.isEmpty()
                ? new ResponseEntity<>(actividades, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(actividades, HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<ActividadResponseDTO> updateActividad(@PathVariable Integer id, @Valid @RequestBody ActividadRequestDTO request) {
        ActividadResponseDTO actividad = actividadService.updateActividad(id, request);
        return new ResponseEntity<>(actividad, HttpStatus.OK);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> deleteActividad(@PathVariable Integer id) {
        actividadService.deleteActividad(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
