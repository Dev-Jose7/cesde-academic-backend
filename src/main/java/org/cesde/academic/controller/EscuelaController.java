package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.dto.request.EscuelaRequestDTO;
import org.cesde.academic.dto.response.EscuelaResponseDTO;
import org.cesde.academic.service.IEscuelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/escuela")
public class EscuelaController {

    @Autowired
    IEscuelaService escuelaService;

    @PostMapping("/crear")
    @PreAuthorize("hasRole('ROLE_DIRECTIVO') or hasRole('ROLE_DEV') and hasAuthority('CREATE')")
    public ResponseEntity<EscuelaResponseDTO> createEscuela(@Valid @RequestBody EscuelaRequestDTO request){
        EscuelaResponseDTO newEscuela = escuelaService.createEscuela(request);
        return new ResponseEntity<>(newEscuela, HttpStatus.CREATED);
    }

    @GetMapping("/lista")
    @PreAuthorize("hasRole('ROLE_DIRECTIVO') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List> getEscuelas(){
        List<EscuelaResponseDTO> escuelas = escuelaService.getEscuelas();

        if(escuelas.isEmpty()){
            return new ResponseEntity<>(escuelas, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(escuelas, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_DIRECTIVO') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<?> getEscuelaById(@PathVariable Integer id){
        EscuelaResponseDTO escuela = escuelaService.getEscuelaById(id);
        return new ResponseEntity<>(escuela, HttpStatus.OK);
    }

    @GetMapping("/buscar/{nombre}")
    @PreAuthorize("hasRole('ROLE_DIRECTIVO') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<EscuelaResponseDTO>> getEscuelaByNombre(@PathVariable String nombre){
        List<EscuelaResponseDTO> escuelas = escuelaService.getEscuelasByNombre(nombre);
        return new ResponseEntity<>(escuelas, HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    @PreAuthorize("hasRole('ROLE_DIRECTIVO') or hasRole('ROLE_DEV') and hasAuthority('UPDATE')")
    public ResponseEntity<EscuelaResponseDTO> updateEscuela(@PathVariable Integer id, @Valid @RequestBody EscuelaRequestDTO request){
        EscuelaResponseDTO updatedEscuela = escuelaService.updateEscuela(id, request);
        return new ResponseEntity<>(updatedEscuela, HttpStatus.OK);
    }

    @DeleteMapping("/remover/{id}")
    @PreAuthorize("hasRole('ROLE_DEV') and hasAuthority('DELETE')")
    public ResponseEntity<Void> deleteEscuela(@PathVariable Integer id){
        escuelaService.deleteEscuela(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
