package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.dto.request.PensumRequestDTO;
import org.cesde.academic.dto.response.PensumResponseDTO;
import org.cesde.academic.enums.NivelPensum;
import org.cesde.academic.service.IPensumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pensum")
public class PensumController {

    @Autowired
    private IPensumService pensumService;

    @PostMapping("/crear")
    public ResponseEntity<PensumResponseDTO> createPensum(@Valid @RequestBody PensumRequestDTO request){
        return new ResponseEntity<>(pensumService.createPensum(request), HttpStatus.OK);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<PensumResponseDTO>> getPensums(){
        List<PensumResponseDTO> pensums = pensumService.getPensums();
        return pensums.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(pensums, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PensumResponseDTO> getPensumById(@PathVariable Integer id){
        return new ResponseEntity<>(pensumService.getPensuById(id), HttpStatus.OK);
    }

    @GetMapping("/programa/{id}")
    public ResponseEntity<List<PensumResponseDTO>> getPensumByProgramaId(@PathVariable Integer id){
        List<PensumResponseDTO> pensums = pensumService.getPensumByProgramaId(id);
        return pensums.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(pensums, HttpStatus.OK);
    }

    @GetMapping("/modulo/{id}")
    public ResponseEntity<List<PensumResponseDTO>> getPensumByModuloId(@PathVariable Integer id){
        List<PensumResponseDTO> pensums = pensumService.getPensumByModuloId(id);
        return pensums.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(pensums, HttpStatus.OK);
    }

    @GetMapping("/nivel/{nivel}")
    public ResponseEntity<List<PensumResponseDTO>> getPensumByNivel(@PathVariable NivelPensum nivel){
        List<PensumResponseDTO> pensums = pensumService.getPensumByNivel(nivel);
        return pensums.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(pensums, HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<PensumResponseDTO> updatePensum(@PathVariable Integer id,
                                                          @Valid @RequestBody PensumRequestDTO request){
        return new ResponseEntity<>(pensumService.updatePensum(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<PensumResponseDTO> deletePensum(@PathVariable Integer id){
        pensumService.deletePensum(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
