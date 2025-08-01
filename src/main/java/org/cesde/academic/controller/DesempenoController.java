package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.dto.request.DesempenoRequestDTO;
import org.cesde.academic.dto.response.DesempenoResponseDTO;
import org.cesde.academic.enums.EstadoDesempeno;
import org.cesde.academic.service.IDesempenoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/desempeno")
public class DesempenoController {

    @Autowired
    private IDesempenoService desempenoService;

    @PostMapping("/crear")
    @PreAuthorize("hasRole('ROLE_DOCENTE') or hasRole('ROLE_DEV') and hasAuthority('CREATE')")
    public ResponseEntity<DesempenoResponseDTO> createDesempeno(@Valid @RequestBody DesempenoRequestDTO request){
        DesempenoResponseDTO desempeno = desempenoService.createDesempeno(request);
        return new ResponseEntity<>(desempeno, HttpStatus.CREATED);
    }

    @GetMapping("/lista")
    @PreAuthorize("hasRole('ROLE_DOCENTE') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<DesempenoResponseDTO>> getDesempenos(){
        List<DesempenoResponseDTO> desempenos = desempenoService.getDesempenos();
        return desempenos.isEmpty()
                ? new ResponseEntity<>(desempenos, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(desempenos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_DOCENTE') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<DesempenoResponseDTO> getDesempenoById(@PathVariable Integer id){
        DesempenoResponseDTO desempeno = desempenoService.getDesempenoById(id);
        return new ResponseEntity<>(desempeno, HttpStatus.OK);
    }

    @GetMapping("/estudiante/{id}")
    @PreAuthorize("hasRole('ROLE_DOCENTE') or hasRole('ROLE_ESTUDIANTE') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<DesempenoResponseDTO>> getDesempenoByEstudiante(@PathVariable Integer id){
        List<DesempenoResponseDTO> desempenos = desempenoService.getDesempenoByEstudiante(id);
        return desempenos.isEmpty()
                ? new ResponseEntity<>(desempenos, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(desempenos, HttpStatus.OK);
    }

    @GetMapping("/modulo/{id}")
    @PreAuthorize("hasRole('ROLE_DOCENTE') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<DesempenoResponseDTO>> getDesempenoByModulo(@PathVariable Integer id){
        List<DesempenoResponseDTO> desempenos = desempenoService.getDesempenoByModulo(id);
        return desempenos.isEmpty()
                ? new ResponseEntity<>(desempenos, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(desempenos, HttpStatus.OK);
    }

    @GetMapping("/estado/{estado}")
    @PreAuthorize("hasRole('ROLE_DOCENTE') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<DesempenoResponseDTO>> getDesempenoByEstado(@PathVariable EstadoDesempeno estado){
        List<DesempenoResponseDTO> desempenos = desempenoService.getDesempenoByEstado(estado);
        return desempenos.isEmpty()
                ? new ResponseEntity<>(desempenos, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(desempenos, HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    @PreAuthorize("hasRole('ROLE_DOCENTE') or hasRole('ROLE_DEV') and hasAuthority('PUT')")
    public ResponseEntity<DesempenoResponseDTO> updateDesempeno(@PathVariable Integer id, @Valid @RequestBody DesempenoRequestDTO request){
        DesempenoResponseDTO desempeno = desempenoService.updateDesempeno(id, request);
        return new ResponseEntity<>(desempeno, HttpStatus.OK);
    }

    @DeleteMapping("/remover/{id}")
    @PreAuthorize("hasRole('ROLE_DEV') and hasAuthority('DELETE')")
    public ResponseEntity<Void> deleteDesempeno(@PathVariable Integer id){
        desempenoService.deleteDesempeno(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
