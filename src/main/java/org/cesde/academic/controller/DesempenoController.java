package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.dto.request.DesempenoRequestDTO;
import org.cesde.academic.dto.response.DesempenoResponseDTO;
import org.cesde.academic.enums.EstadoDesempeno;
import org.cesde.academic.service.IDesempenoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/desempeno")
public class DesempenoController {

    @Autowired
    private IDesempenoService desempenoService;

    @PostMapping("/crear")
    public ResponseEntity<DesempenoResponseDTO> createDesempeno(@Valid @RequestBody DesempenoRequestDTO request){
        DesempenoResponseDTO desempeno = desempenoService.createDesempeno(request);
        return new ResponseEntity<>(desempeno, HttpStatus.CREATED);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<DesempenoResponseDTO>> getDesempenos(){
        List<DesempenoResponseDTO> desempenos = desempenoService.getDesempenos();
        return desempenos.isEmpty()
                ? new ResponseEntity<>(desempenos, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(desempenos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DesempenoResponseDTO> getDesempenoById(@PathVariable Integer id){
        DesempenoResponseDTO desempeno = desempenoService.getDesempenoById(id);
        return new ResponseEntity<>(desempeno, HttpStatus.OK);
    }

    @GetMapping("/estudiante/{id}")
    public ResponseEntity<List<DesempenoResponseDTO>> getDesempenoByEstudiante(@PathVariable Integer id){
        List<DesempenoResponseDTO> desempenos = desempenoService.getDesempenoByEstudiante(id);
        return desempenos.isEmpty()
                ? new ResponseEntity<>(desempenos, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(desempenos, HttpStatus.OK);
    }

    @GetMapping("/modulo/{id}")
    public ResponseEntity<List<DesempenoResponseDTO>> getDesempenoByModulo(@PathVariable Integer id){
        List<DesempenoResponseDTO> desempenos = desempenoService.getDesempenoByModulo(id);
        return desempenos.isEmpty()
                ? new ResponseEntity<>(desempenos, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(desempenos, HttpStatus.OK);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<DesempenoResponseDTO>> getDesempenoByEstado(@PathVariable EstadoDesempeno estado){
        List<DesempenoResponseDTO> desempenos = desempenoService.getDesempenoByEstado(estado);
        return desempenos.isEmpty()
                ? new ResponseEntity<>(desempenos, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(desempenos, HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<DesempenoResponseDTO> updateDesempeno(@PathVariable Integer id, @Valid @RequestBody DesempenoRequestDTO request){
        DesempenoResponseDTO desempeno = desempenoService.updateDesempeno(id, request);
        return new ResponseEntity<>(desempeno, HttpStatus.OK);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> deleteDesempeno(@PathVariable Integer id){
        desempenoService.deleteDesempeno(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
