package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.dto.request.ProgramaRequestDTO;
import org.cesde.academic.dto.response.ProgramaResponseDTO;
import org.cesde.academic.service.IProgramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programa")
public class ProgramaController {

    @Autowired
    IProgramaService programaService;

    // Endpoint para crear un nuevo programa
    @PostMapping("/crear")
    @PreAuthorize("hasRole('ROLE_DIRECTIVO') or hasRole('ROLE_DEV') and hasAuthority('CREATE')")
    public ResponseEntity<ProgramaResponseDTO> createPrograma(@Valid @RequestBody ProgramaRequestDTO request){
        ProgramaResponseDTO newPrograma =  programaService.createPrograma(request);
        return new ResponseEntity<>(newPrograma, HttpStatus.CREATED);
    }

    // Endpoint para obtener la lista de programas
    @GetMapping("/lista")
    @PreAuthorize("hasRole('ROLE_DIRECTIVO') or hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<ProgramaResponseDTO>> getListaProgramas(){
        List<ProgramaResponseDTO> programaList = programaService.getProgramas();

        if(programaList.isEmpty()){
            return new ResponseEntity<>(programaList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(programaList, HttpStatus.OK);
        }
    }

    // Endpoint para obtener un programa por su ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_DIRECTIVO') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<ProgramaResponseDTO> getProgramaById(@PathVariable Integer id){
        ProgramaResponseDTO programa = programaService.getProgramaById(id);
        return new ResponseEntity<>(programa, HttpStatus.OK);
    }

    @GetMapping("/buscar/{nombre}")
    @PreAuthorize("hasRole('ROLE_DIRECTIVO') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<ProgramaResponseDTO>> getProgramaByNombre(@PathVariable String nombre){
        List<ProgramaResponseDTO> programaList = programaService.getProgramasByNombre(nombre);

        if(programaList.isEmpty()){
            return new ResponseEntity<>(programaList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(programaList, HttpStatus.OK);
        }
    }

    // Endpoint para obtener programas de una escuela específica
    @GetMapping("/escuela/{id}")
    @PreAuthorize("hasRole('ROLE_DIRECTIVO') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<ProgramaResponseDTO>> getProgramasByEscuelaId(@PathVariable Integer id) {
        List<ProgramaResponseDTO> programaList = programaService.getProgramasByEscuelaId(id);

        if (programaList.isEmpty()) {
            return new ResponseEntity<>(programaList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(programaList, HttpStatus.OK);
        }
    }

    // Endpoint para actualizar un programa
    @PutMapping("/editar/{id}")
    @PreAuthorize("hasRole('ROLE_DIRECTIVO') or hasRole('ROLE_DEV') and hasAuthority('UPDATE')")
    public ResponseEntity<ProgramaResponseDTO> updatePrograma(@PathVariable Integer id, @Valid @RequestBody ProgramaRequestDTO request){
        ProgramaResponseDTO programa = programaService.updatePrograma(id, request);
        return new ResponseEntity<>(programa, HttpStatus.OK);
    }

    // Endpoint para eliminar un programa
    @DeleteMapping("/remover/{id}")
    @PreAuthorize("hasRole('ROLE_DEV') and hasAuthority('DELETE')")
    public ResponseEntity<Void> deletePrograma(@PathVariable Integer id){
        programaService.deletePrograma(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
