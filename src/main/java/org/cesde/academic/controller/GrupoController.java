package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.model.Grupo;
import org.cesde.academic.service.IGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/grupo")
public class GrupoController {

    @Autowired
    IGrupoService grupoService;

    // Endpoint para crear un nuevo grupo
    @PostMapping("/crear")
    public ResponseEntity<Grupo> createGrupo(@Valid @RequestBody Grupo grupo){
        Grupo newGrupo = grupoService.createGrupo(grupo);
        return new ResponseEntity<>(newGrupo, HttpStatus.CREATED);
    }

    // Endpoint para obtener la lista de grupos
    @GetMapping("/lista")
    public ResponseEntity<List<Grupo>> getListaGrupos(){
        List<Grupo> grupoList = grupoService.getGrupos();

        if(grupoList.isEmpty()){
            return new ResponseEntity<>(grupoList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(grupoList, HttpStatus.OK);
        }
    }

    // Endpoint para obtener un grupo por su ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getGrupoById(@PathVariable Integer id){
        Optional<Grupo> optionalGrupo = grupoService.getGrupoById(id);

        return optionalGrupo.map(grupo -> new ResponseEntity<>(grupo, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para obtener los grupos de un programa
    @GetMapping("/programa/{programaId}")
    public ResponseEntity<List<Grupo>> getGruposByProgramaId(@PathVariable Integer programaId){
        List<Grupo> grupoList = grupoService.getGruposByProgramaId(programaId);

        if(grupoList.isEmpty()){
            return new ResponseEntity<>(grupoList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(grupoList, HttpStatus.OK);
        }
    }

    // Endpoint para obtener los grupos de un semestre
    @GetMapping("/semestre/{semestreId}")
    public ResponseEntity<List<Grupo>> getGruposBySemestreId(@PathVariable Integer semestreId){
        List<Grupo> grupoList = grupoService.getGruposBySemestreId(semestreId);

        if(grupoList.isEmpty()){
            return new ResponseEntity<>(grupoList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(grupoList, HttpStatus.OK);
        }
    }

    // Endpoint para actualizar un grupo
    @PutMapping("/editar/{id}")
    public ResponseEntity<Grupo> updateGrupo(@PathVariable Integer id, @Valid @RequestBody Grupo updatedGrupo){
        Optional<Grupo> optionalGrupo = grupoService.getGrupoById(id);

        return optionalGrupo
                .map(grupo -> new ResponseEntity<>(grupoService.updateGrupo(grupo, updatedGrupo), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para eliminar un grupo
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Grupo> deleteGrupo(@PathVariable Integer id){
        Optional<Grupo> optionalGrupo = grupoService.getGrupoById(id);

        if(optionalGrupo.isPresent()){
            grupoService.deleteGrupo(optionalGrupo.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
