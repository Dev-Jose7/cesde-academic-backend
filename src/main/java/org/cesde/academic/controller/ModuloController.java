package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.model.Modulo;
import org.cesde.academic.service.IModuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/modulo")
public class ModuloController {

    @Autowired
    IModuloService moduloService;

    // Endpoint para crear un nuevo módulo
    @PostMapping("/crear")
    public ResponseEntity<Modulo> createModulo(@Valid @RequestBody Modulo modulo){
        Optional<Modulo> moduloOptional = moduloService.getModuloByNombre(modulo.getNombre());

        if(moduloOptional.isEmpty()){
            Modulo newModulo = moduloService.createModulo(modulo);
            return new ResponseEntity<>(newModulo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    // Endpoint para obtener la lista de módulos
    @GetMapping("/lista")
    public ResponseEntity<List<Modulo>> getListaModulos(){
        List<Modulo> moduloList = moduloService.getModulos();

        if(moduloList.isEmpty()){
            return new ResponseEntity<>(moduloList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(moduloList, HttpStatus.OK);
        }
    }

    // Endpoint para obtener un módulo por su ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getModuloById(@PathVariable Integer id){
        Optional<Modulo> optionalModulo = moduloService.getModuloById(id);

        return optionalModulo.map(modulo -> new ResponseEntity<>(modulo, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para actualizar un módulo
    @PutMapping("/editar/{id}")
    public ResponseEntity<Modulo> updateModulo(@PathVariable Integer id, @Valid @RequestBody Modulo updatedModulo){
        Optional<Modulo> moduloById = moduloService.getModuloById(id);
        Optional<Modulo> moduloByNombre = moduloService.getModuloByNombre(updatedModulo.getNombre());

        if(moduloById.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(moduloByNombre.isPresent() && moduloById.get().getId() != id){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(moduloService.updateModulo(moduloById.get(), updatedModulo), HttpStatus.OK);
    }

    // Endpoint para eliminar un módulo
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Modulo> deleteModulo(@PathVariable Integer id){
        Optional<Modulo> optionalModulo = moduloService.getModuloById(id);

        if(optionalModulo.isPresent()){
            moduloService.deleteModulo(optionalModulo.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para obtener módulos por el ID del programa
    @GetMapping("/programa/{id}")
    public ResponseEntity<List<Modulo>> getModulosByProgramaId(@PathVariable Integer programaId) {
        List<Modulo> moduloList = moduloService.getModulosByProgramaId(programaId);

        if (moduloList.isEmpty()) {
            return new ResponseEntity<>(moduloList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(moduloList, HttpStatus.OK);
        }
    }
}
