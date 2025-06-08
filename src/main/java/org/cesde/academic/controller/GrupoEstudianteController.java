package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.dto.request.GrupoEstudianteRequestDTO;
import org.cesde.academic.dto.response.GrupoEstudianteResponseDTO;
import org.cesde.academic.model.GrupoEstudianteId;
import org.cesde.academic.service.IGrupoEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupo-estudiante")
public class GrupoEstudianteController {

    @Autowired
    private IGrupoEstudianteService grupoEstudianteService;

    // Crear un nuevo registro grupo-estudiante
    @PostMapping("/crear")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_DEV') and hasAuthority('CREATE')")
    public ResponseEntity<GrupoEstudianteResponseDTO> createGrupoEstudiante(@Valid @RequestBody GrupoEstudianteRequestDTO request) {
        GrupoEstudianteResponseDTO response = grupoEstudianteService.createGrupoEstudiante(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Obtener todos los registros
    @GetMapping("/lista")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<GrupoEstudianteResponseDTO>> getListaGrupoEstudiantes() {
        List<GrupoEstudianteResponseDTO> lista = grupoEstudianteService.getGrupoEstudiantes();

        return lista.isEmpty()
                ? new ResponseEntity<>(lista, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(lista, HttpStatus.OK);
    }

    // Obtener un registro por ID compuesto
    @GetMapping("/{grupoId}/{estudianteId}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<GrupoEstudianteResponseDTO> getGrupoEstudianteById(@PathVariable Integer grupoId, @PathVariable Integer estudianteId) {
        GrupoEstudianteId id = new GrupoEstudianteId(grupoId, estudianteId);
        GrupoEstudianteResponseDTO response = grupoEstudianteService.getGrupoEstudianteById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Obtener todos los estudiantes de un grupo
    @GetMapping("/grupo/{grupoId}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<GrupoEstudianteResponseDTO>> getGrupoEstudiantesByGrupoId(@PathVariable Integer grupoId) {
        List<GrupoEstudianteResponseDTO> lista = grupoEstudianteService.getGrupoEstudiantesByGrupoId(grupoId);

        return lista.isEmpty()
                ? new ResponseEntity<>(lista, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(lista, HttpStatus.OK);
    }

    // Obtener todos los grupos de un estudiante
    @GetMapping("/estudiante/{estudianteId}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_ESTUDIANTE') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<GrupoEstudianteResponseDTO>> getGrupoEstudiantesByEstudianteId(@PathVariable Integer estudianteId) {
        List<GrupoEstudianteResponseDTO> lista = grupoEstudianteService.getGrupoEstudiantesByEstudianteId(estudianteId);

        return lista.isEmpty()
                ? new ResponseEntity<>(lista, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(lista, HttpStatus.OK);
    }

    // Actualizar la relación grupo-estudiante
    @PutMapping("/editar/{grupoId}/{estudianteId}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_DEV') and hasAuthority('UPDATE')")
    public ResponseEntity<GrupoEstudianteResponseDTO> updateGrupoEstudiante(@PathVariable Integer grupoId, @PathVariable Integer estudianteId,
                                                                            @Valid @RequestBody GrupoEstudianteRequestDTO request) {
        GrupoEstudianteId id = new GrupoEstudianteId(grupoId, estudianteId);
        GrupoEstudianteResponseDTO response = grupoEstudianteService.updateGrupoEstudiante(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Eliminar una relación grupo-estudiante
    @DeleteMapping("/remover/{grupoId}/{estudianteId}")
    @PreAuthorize("hasRole('ROLE_DEV') and hasAuthority('DELETE')")
    public ResponseEntity<Void> deleteGrupoEstudiante(@PathVariable Integer grupoId, @PathVariable Integer estudianteId) {
        GrupoEstudianteId id = new GrupoEstudianteId(grupoId, estudianteId);
        grupoEstudianteService.deleteGrupoEstudiante(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
