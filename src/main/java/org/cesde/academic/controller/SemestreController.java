package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.dto.request.SemestreRequestDTO;
import org.cesde.academic.dto.response.SemestreResponseDTO;
import org.cesde.academic.service.ISemestreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/semestre")
public class SemestreController {

    @Autowired
    private ISemestreService semestreService;

    @PostMapping("/crear")
    @PreAuthorize("hasRole('ROLE_DIRECTIVO') or hasRole('ROLE_DEV') and hasAuthority('CREATE')")
    public ResponseEntity<SemestreResponseDTO> createSemestre(@Valid @RequestBody SemestreRequestDTO request) {
        SemestreResponseDTO newSemestre = semestreService.createSemestre(request);
        return new ResponseEntity<>(newSemestre, HttpStatus.CREATED);
    }

    @GetMapping("/lista")
    @PreAuthorize("hasRole('ROLE_DIRECTIVO') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<SemestreResponseDTO>> getListaSemestres() {
        List<SemestreResponseDTO> semestreList = semestreService.getSemestres();

        if (semestreList.isEmpty()) {
            return new ResponseEntity<>(semestreList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(semestreList, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_DIRECTIVO') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<SemestreResponseDTO> getSemestreById(@PathVariable Integer id) {
        SemestreResponseDTO semestre = semestreService.getSemestreById(id);
        return new ResponseEntity<>(semestre, HttpStatus.OK);
    }

    @GetMapping("/buscar/{nombre}")
    @PreAuthorize("hasRole('ROLE_DIRECTIVO') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<SemestreResponseDTO>> getSemestresByNombre(@PathVariable String nombre) {
        List<SemestreResponseDTO> semestreList = semestreService.getSemestresByNombre(nombre);

        if (semestreList.isEmpty()) {
            return new ResponseEntity<>(semestreList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(semestreList, HttpStatus.OK);
        }
    }

    @PutMapping("/editar/{id}")
    @PreAuthorize("hasRole('ROLE_DIRECTIVO') or hasRole('ROLE_DEV') and hasAuthority('UPDATE')")
    public ResponseEntity<SemestreResponseDTO> updateSemestre(@PathVariable Integer id, @Valid @RequestBody SemestreRequestDTO request) {
        SemestreResponseDTO updatedSemestre = semestreService.updateSemestre(id, request);
        return new ResponseEntity<>(updatedSemestre, HttpStatus.OK);
    }

    @DeleteMapping("/remover/{id}")
    @PreAuthorize("hasRole('ROLE_DEV') and hasAuthority('DELETE')")
    public ResponseEntity<Void> deleteSemestre(@PathVariable Integer id) {
        semestreService.deleteSemestre(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
