package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.dto.request.DiaRequestDTO;
import org.cesde.academic.dto.response.DiaResponseDTO;
import org.cesde.academic.enums.NombreDia;
import org.cesde.academic.service.IDiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dia")
public class DiaController {

    @Autowired
    private IDiaService diaService;

    @PostMapping("/crear")
    public ResponseEntity<DiaResponseDTO> createDia(@Valid @RequestBody DiaRequestDTO request) {
        return new ResponseEntity<>(diaService.createDia(request), HttpStatus.CREATED);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<DiaResponseDTO>> getDias() {
        List<DiaResponseDTO> dias = diaService.getDias();
        return dias.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(dias, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiaResponseDTO> getDiaById(@PathVariable Integer id) {
        return new ResponseEntity<>(diaService.getDiaById(id), HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<DiaResponseDTO> getDiaByNombre(@RequestParam NombreDia nombre) {
        return new ResponseEntity<>(diaService.getDiaByNombre(nombre), HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<DiaResponseDTO> updateDia(@PathVariable Integer id, @Valid @RequestBody DiaRequestDTO request) {
        return new ResponseEntity<>(diaService.updateDia(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> deleteDia(@PathVariable Integer id) {
        diaService.deleteDia(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
