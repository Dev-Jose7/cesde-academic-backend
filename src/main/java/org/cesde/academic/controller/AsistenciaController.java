package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.dto.request.AsistenciaRequestDTO;
import org.cesde.academic.dto.response.AsistenciaResponseDTO;
import org.cesde.academic.enums.EstadoAsistencia;
import org.cesde.academic.service.IAsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/asistencia")
public class AsistenciaController {

    @Autowired
    private IAsistenciaService asistenciaService;

    @PostMapping("/crear")
    @PreAuthorize("hasRole('ROLE_DOCENTE') or hasRole('ROLE_DEV') and hasAuthority('CREATE')")
    public ResponseEntity<AsistenciaResponseDTO> createAsistencia(@Valid @RequestBody AsistenciaRequestDTO request) {
        return new ResponseEntity<>(asistenciaService.createAsistencia(request), HttpStatus.CREATED);
    }

    @GetMapping("/lista")
    @PreAuthorize("hasRole('ROLE_DOCENTE') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<AsistenciaResponseDTO>> getAsistencias() {
        List<AsistenciaResponseDTO> asistencias = asistenciaService.getAsistencias();
        return asistencias.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(asistencias, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_DOCENTE') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<AsistenciaResponseDTO> getAsistenciaById(@PathVariable Integer id) {
        return new ResponseEntity<>(asistenciaService.getAsistenciaById(id), HttpStatus.OK);
    }

    @GetMapping("/clase/{id}")
    @PreAuthorize("hasRole('ROLE_DOCENTE') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<AsistenciaResponseDTO>> getAsistenciasByClaseId(@PathVariable Integer id) {
        List<AsistenciaResponseDTO> asistencias = asistenciaService.getAsistenciasByClaseId(id);
        return asistencias.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(asistencias, HttpStatus.OK);
    }

    @GetMapping("/estudiante/{id}")
    @PreAuthorize("hasRole('ROLE_DOCENTE') or hasRole('ROLE_ESTUDIANTE') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<AsistenciaResponseDTO>> getAsistenciasByEstudianteId(@PathVariable Integer id) {
        List<AsistenciaResponseDTO> asistencias = asistenciaService.getAsistenciasByEstudianteId(id);
        return asistencias.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(asistencias, HttpStatus.OK);
    }

    @GetMapping("/buscar/fecha")
    @PreAuthorize("hasRole('ROLE_DOCENTE') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<AsistenciaResponseDTO>> getAsistenciasByFechaRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate hasta) {
        List<AsistenciaResponseDTO> asistencias = asistenciaService.getAsistenciasByFechaRange(desde, hasta);
        return asistencias.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(asistencias, HttpStatus.OK);
    }

    @GetMapping("/buscar/estado/{estado}")
    @PreAuthorize("hasRole('ROLE_DOCENTE') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<AsistenciaResponseDTO>> getAsistenciasByEstado(@PathVariable EstadoAsistencia estado) {
        List<AsistenciaResponseDTO> asistencias = asistenciaService.getAsistenciasByEstado(estado);
        return asistencias.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(asistencias, HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    @PreAuthorize("hasRole('ROLE_DOCENTE') or hasRole('ROLE_DEV') and hasAuthority('UPDATE')")
    public ResponseEntity<AsistenciaResponseDTO> updateAsistencia(@PathVariable Integer id,
                                                                  @Valid @RequestBody AsistenciaRequestDTO request) {
        return new ResponseEntity<>(asistenciaService.updateAsistencia(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/remover/{id}")
    @PreAuthorize("hasRole('ROLE_DEV') and hasAuthority('DELETE')")
    public ResponseEntity<Void> deleteAsistencia(@PathVariable Integer id) {
        asistenciaService.deleteAsistencia(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
