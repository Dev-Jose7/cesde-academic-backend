package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.dto.request.ClaseHorarioRequestDTO;
import org.cesde.academic.dto.response.ClaseHorarioResponseDTO;
import org.cesde.academic.model.ClaseHorarioId;
import org.cesde.academic.service.IClaseHorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clase-horario")
public class ClaseHorarioController {

    @Autowired
    private IClaseHorarioService claseHorarioService;

    // Crear un nuevo registro clase-horario
    @PostMapping("/crear")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_DEV') and hasAuthority('CREATE')")
    public ResponseEntity<ClaseHorarioResponseDTO> createClaseHorario(@Valid @RequestBody ClaseHorarioRequestDTO request) {
        ClaseHorarioResponseDTO response = claseHorarioService.createClaseHorario(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Obtener todos los registros
    @GetMapping("/lista")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<ClaseHorarioResponseDTO>> getListaClaseHorarios() {
        List<ClaseHorarioResponseDTO> lista = claseHorarioService.getClaseHorarios();

        return lista.isEmpty()
                ? new ResponseEntity<>(lista, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(lista, HttpStatus.OK);
    }

    // Obtener un registro por ID compuesto
    @GetMapping("/{claseId}/{horarioId}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<ClaseHorarioResponseDTO> getClaseHorarioById(@PathVariable Integer claseId, @PathVariable Integer horarioId) {
        ClaseHorarioId id = new ClaseHorarioId(claseId, horarioId);
        ClaseHorarioResponseDTO response = claseHorarioService.getClaseHorarioById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Obtener todos los horarios de una clase
    @GetMapping("/clase/{claseId}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_ESTUDIANTE') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<ClaseHorarioResponseDTO>> getClaseHorariosByClaseId(@PathVariable Integer claseId) {
        List<ClaseHorarioResponseDTO> lista = claseHorarioService.getClaseHorariosByClaseId(claseId);

        return lista.isEmpty()
                ? new ResponseEntity<>(lista, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(lista, HttpStatus.OK);
    }

    // Obtener todos los horarios por horario
    @GetMapping("/horario/{horarioId}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_ESTUDIANTE') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<ClaseHorarioResponseDTO>> getClaseHorariosByHorarioId(@PathVariable Integer horarioId) {
        List<ClaseHorarioResponseDTO> lista = claseHorarioService.getClaseHorariosByHorarioId(horarioId);

        return lista.isEmpty()
                ? new ResponseEntity<>(lista, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(lista, HttpStatus.OK);
    }

    // Actualizar un registro
    @PutMapping("/{claseId}/{horarioId}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_DEV') and hasAuthority('UPDATE')")
    public ResponseEntity<ClaseHorarioResponseDTO> updateClaseHorario(@PathVariable Integer claseId,
                                                                      @PathVariable Integer horarioId,
                                                                      @Valid @RequestBody ClaseHorarioRequestDTO request) {
        ClaseHorarioId id = new ClaseHorarioId(claseId, horarioId);
        ClaseHorarioResponseDTO response = claseHorarioService.updateClaseHorario(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Eliminar un registro
    @DeleteMapping("/{claseId}/{horarioId}")
    @PreAuthorize("hasRole('ROLE_DEV') and hasAuthority('DELETE')")
    public ResponseEntity<Void> deleteClaseHorario(@PathVariable Integer claseId,
                                                   @PathVariable Integer horarioId) {
        ClaseHorarioId id = new ClaseHorarioId(claseId, horarioId);
        claseHorarioService.deleteClaseHorario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
