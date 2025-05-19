package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.dto.request.GrupoRequestDTO;
import org.cesde.academic.dto.response.GrupoResponseDTO;
import org.cesde.academic.enums.EstadoGrupo;
import org.cesde.academic.service.IGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupo")
public class GrupoController {

    @Autowired
    private IGrupoService grupoService;

    @PostMapping("/crear")
    public ResponseEntity<GrupoResponseDTO> createGrupo(@Valid @RequestBody GrupoRequestDTO request){
        GrupoResponseDTO grupo = grupoService.createGrupo(request);
        return new ResponseEntity<>(grupo, HttpStatus.CREATED);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<GrupoResponseDTO>> getListaGrupos(){
        List<GrupoResponseDTO> grupos = grupoService.getGrupos();
        return grupos.isEmpty()
                ? new ResponseEntity<>(grupos, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(grupos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GrupoResponseDTO> getGrupoById(@PathVariable Integer id){
        GrupoResponseDTO grupo = grupoService.getGrupoById(id);
        return new ResponseEntity<>(grupo, HttpStatus.OK);
    }

    @GetMapping("/programa/{id}")
    public ResponseEntity<List<GrupoResponseDTO>> getGruposByProgramaId(@PathVariable Integer id){
        List<GrupoResponseDTO> grupos = grupoService.getGruposByProgramaId(id);
        return grupos.isEmpty()
                ? new ResponseEntity<>(grupos, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(grupos, HttpStatus.OK);
    }

    @GetMapping("/semestre/{id}")
    public ResponseEntity<List<GrupoResponseDTO>> getGruposBySemestreId(@PathVariable Integer id){
        List<GrupoResponseDTO> grupos = grupoService.getGruposBySemestreId(id);
        return grupos.isEmpty()
                ? new ResponseEntity<>(grupos, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(grupos, HttpStatus.OK);
    }

    @GetMapping("/buscar/codigo/{codigo}")
    public ResponseEntity<List<GrupoResponseDTO>> getGruposByCodigo(@PathVariable("codigo") String codigo) {
        List<GrupoResponseDTO> grupos = grupoService.getGruposByCodigo(codigo);
        return grupos.isEmpty()
                ? new ResponseEntity<>(grupos, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(grupos, HttpStatus.OK);
    }

    @GetMapping("/buscar/estado/{estado}")
    public ResponseEntity<List<GrupoResponseDTO>> getGrupoByEstado(@PathVariable EstadoGrupo estado){
        List<GrupoResponseDTO> grupos = grupoService.getGruposByEstado(estado);
        return grupos.isEmpty()
                ? new ResponseEntity<>(grupos, HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(grupos, HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<GrupoResponseDTO> updateGrupo(@PathVariable Integer id, @Valid @RequestBody GrupoRequestDTO request){
        GrupoResponseDTO grupo = grupoService.updateGrupo(id, request);
        return new ResponseEntity<>(grupo, HttpStatus.OK);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> deleteGrupo(@PathVariable Integer id){
        grupoService.deleteGrupo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
