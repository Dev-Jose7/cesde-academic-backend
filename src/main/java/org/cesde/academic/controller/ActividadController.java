package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.dto.request.ActividadRequestDTO;
import org.cesde.academic.dto.response.ActividadResponseDTO;
import org.cesde.academic.enums.TipoActividad;
import org.cesde.academic.service.IActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actividad")
public class ActividadController {

    @Autowired
    private IActividadService actividadService;

    @PostMapping("/crear")
    @PreAuthorize("hasRole('ROLE_DOCENTE') or hasRole('ROLE_DEV') and hasAuthority('CREATE')")
    public ResponseEntity<ActividadResponseDTO> createActividad(@Valid @RequestBody ActividadRequestDTO request) {
        ActividadResponseDTO actividad = actividadService.createActividad(request);
        return new ResponseEntity<>(actividad, HttpStatus.CREATED);
    }

    @GetMapping("/lista")
    @PreAuthorize("hasRole('ROLE_DOCENTE') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<ActividadResponseDTO>> getActividades() {
        List<ActividadResponseDTO> actividades = actividadService.getActividades();
        return actividades.isEmpty()
                ? new ResponseEntity<>(actividades, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(actividades, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_DOCENTE') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<ActividadResponseDTO> getActividadById(@PathVariable Integer id) {
        ActividadResponseDTO actividad = actividadService.getActividadById(id);
        return new ResponseEntity<>(actividad, HttpStatus.OK);
    }

    @GetMapping("/clase/{id}")
    @PreAuthorize("hasRole('ROLE_DOCENTE') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<ActividadResponseDTO>> getActividadesByClase(@PathVariable Integer claseId) {
        List<ActividadResponseDTO> actividades = actividadService.getActividadesByClase(claseId);
        return actividades.isEmpty()
                ? new ResponseEntity<>(actividades, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(actividades, HttpStatus.OK);
    }

    @GetMapping("/clase/buscar/titulo/{titulo}")
    @PreAuthorize("hasRole('ROLE_DOCENTE') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<ActividadResponseDTO>> getActividadesByTitulo(@PathVariable String titulo){
        List<ActividadResponseDTO> actividades = actividadService.getActividadesByTitulo(titulo);
        return actividades.isEmpty()
                ? new ResponseEntity<>(actividades, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(actividades, HttpStatus.OK);
    }

    @GetMapping("/clase/buscar/tipo/{tipo}")
    @PreAuthorize("hasRole('ROLE_DOCENTE') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<ActividadResponseDTO>> getActividadesByTipo(@PathVariable TipoActividad tipo){
        List<ActividadResponseDTO> actividades = actividadService.getActividadesByTipo(tipo);
        return actividades.isEmpty()
                ? new ResponseEntity<>(actividades, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(actividades, HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    @PreAuthorize("hasRole('ROLE_DOCENTE') or hasRole('ROLE_DEV') and hasAuthority('UPDATE')")
    public ResponseEntity<ActividadResponseDTO> updateActividad(@PathVariable Integer id, @Valid @RequestBody ActividadRequestDTO request) {
        ActividadResponseDTO actividad = actividadService.updateActividad(id, request);
        return new ResponseEntity<>(actividad, HttpStatus.OK);
    }

    @DeleteMapping("/remover/{id}")
    @PreAuthorize("hasRole('ROLE_DEV') and hasAuthority('DELETE')")
    public ResponseEntity<Void> deleteActividad(@PathVariable Integer id) {
        actividadService.deleteActividad(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
