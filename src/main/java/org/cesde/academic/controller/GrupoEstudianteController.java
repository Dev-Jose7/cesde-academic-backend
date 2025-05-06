package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.model.GrupoEstudiante;
import org.cesde.academic.model.GrupoEstudianteId;
import org.cesde.academic.service.IGrupoEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/grupo-estudiante")
public class GrupoEstudianteController {

    @Autowired
    private IGrupoEstudianteService grupoEstudianteService;

    // Endpoint para crear un nuevo grupo-estudiante
    @PostMapping("/crear")
    public ResponseEntity<GrupoEstudiante> createGrupoEstudiante(@Valid @RequestBody GrupoEstudiante grupoEstudiante) {
        GrupoEstudiante newGrupoEstudiante = grupoEstudianteService.createGrupoEstudiante(grupoEstudiante);
        return new ResponseEntity<>(newGrupoEstudiante, HttpStatus.CREATED);
    }

    // Endpoint para obtener todos los grupo-estudiantes
    @GetMapping("/lista")
    public ResponseEntity<List<GrupoEstudiante>> getListaGrupoEstudiantes() {
        List<GrupoEstudiante> grupoEstudianteList = grupoEstudianteService.getGrupoEstudiantes();

        if (grupoEstudianteList.isEmpty()) {
            return new ResponseEntity<>(grupoEstudianteList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(grupoEstudianteList, HttpStatus.OK);
        }
    }

    // Endpoint para obtener un grupo-estudiante por su ID (ID compuesto)
    @GetMapping("/{grupoId}/{estudianteId}")
    public ResponseEntity<?> getGrupoEstudianteById(@PathVariable Integer grupoId, @PathVariable Integer estudianteId) {
        GrupoEstudianteId grupoEstudianteId = new GrupoEstudianteId(grupoId, estudianteId);
        Optional<GrupoEstudiante> optionalGrupoEstudiante = grupoEstudianteService.getGrupoEstudianteById(grupoEstudianteId);

        return optionalGrupoEstudiante
                .map(grupoEstudiante -> new ResponseEntity<>(grupoEstudiante, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para obtener los grupo-estudiantes de un grupo
    @GetMapping("/grupo/{id}")
    public ResponseEntity<List<GrupoEstudiante>> getGrupoEstudiantesByGrupoId(@PathVariable Integer grupoId) {
        List<GrupoEstudiante> grupoEstudianteList = grupoEstudianteService.getGrupoEstudiantesByGrupoId(grupoId);

        if (grupoEstudianteList.isEmpty()) {
            return new ResponseEntity<>(grupoEstudianteList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(grupoEstudianteList, HttpStatus.OK);
        }
    }

    // Endpoint para obtener los grupo-estudiantes de un estudiante
    @GetMapping("/estudiante/{id}")
    public ResponseEntity<List<GrupoEstudiante>> getGrupoEstudiantesByEstudianteId(@PathVariable Integer estudianteId) {
        List<GrupoEstudiante> grupoEstudianteList = grupoEstudianteService.getGrupoEstudiantesByEstudianteId(estudianteId);

        if (grupoEstudianteList.isEmpty()) {
            return new ResponseEntity<>(grupoEstudianteList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(grupoEstudianteList, HttpStatus.OK);
        }
    }

    // Endpoint para actualizar un grupo-estudiante
    @PutMapping("/editar/{grupoId}/{estudianteId}")
    public ResponseEntity<GrupoEstudiante> updateGrupoEstudiante(@PathVariable Integer grupoId,
                                                                 @PathVariable Integer estudianteId,
                                                                 @Valid @RequestBody GrupoEstudiante updatedGrupoEstudiante) {
        GrupoEstudianteId grupoEstudianteId = new GrupoEstudianteId(grupoId, estudianteId);
        Optional<GrupoEstudiante> optionalGrupoEstudiante = grupoEstudianteService.getGrupoEstudianteById(grupoEstudianteId);

        return optionalGrupoEstudiante
                .map(grupoEstudiante -> new ResponseEntity<>(grupoEstudianteService.updateGrupoEstudiante(grupoEstudiante, updatedGrupoEstudiante), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para eliminar un grupo-estudiante
    @DeleteMapping("/remover/{grupoId}/{estudianteId}")
    public ResponseEntity<GrupoEstudiante> deleteGrupoEstudiante(@PathVariable Integer grupoId, @PathVariable Integer estudianteId) {
        GrupoEstudianteId grupoEstudianteId = new GrupoEstudianteId(grupoId, estudianteId);
        Optional<GrupoEstudiante> optionalGrupoEstudiante = grupoEstudianteService.getGrupoEstudianteById(grupoEstudianteId);

        if (optionalGrupoEstudiante.isPresent()) {
            grupoEstudianteService.deleteGrupoEstudiante(optionalGrupoEstudiante.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
