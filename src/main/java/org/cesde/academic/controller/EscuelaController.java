package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.dto.request.EscuelaRequestDTO;
import org.cesde.academic.dto.response.EscuelaResponseDTO;
import org.cesde.academic.service.IEscuelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/escuela")
public class EscuelaController {

    @Autowired
    IEscuelaService escuelaService;

    @PostMapping("/crear")
    public ResponseEntity<EscuelaResponseDTO> createEscuela(@Valid @RequestBody EscuelaRequestDTO request){
        EscuelaResponseDTO newEscuela = escuelaService.createEscuela(request);
        return new ResponseEntity<>(newEscuela, HttpStatus.CREATED);
    }

    @GetMapping("/lista")
    public ResponseEntity<List> getEscuelas(){
        List<EscuelaResponseDTO> escuelaList = escuelaService.getEscuelas();

        if(escuelaList.isEmpty()){
            return new ResponseEntity<>(escuelaList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(escuelaList, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEscuelaById(@PathVariable Integer id){
        EscuelaResponseDTO escuela = escuelaService.getEscuelaById(id);
        return new ResponseEntity<>(escuela, HttpStatus.OK);
    }

    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<EscuelaResponseDTO>> getEscuelaByNombre(@PathVariable String nombre){
        List<EscuelaResponseDTO> escuelas = escuelaService.getEscuelaByNombre(nombre);
        return new ResponseEntity<>(escuelas, HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<EscuelaResponseDTO> updateEscuela(@PathVariable Integer id, @Valid @RequestBody EscuelaRequestDTO request){
        EscuelaResponseDTO updatedEscuela = escuelaService.updateEscuela(id, request);
        return new ResponseEntity<>(updatedEscuela, HttpStatus.OK);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> deleteEscuela(@PathVariable Integer id){
        escuelaService.deleteEscuela(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
