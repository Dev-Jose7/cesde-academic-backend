package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.model.Anuncio;
import org.cesde.academic.service.IAnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/anuncio")
public class AnuncioController {

    @Autowired
    private IAnuncioService anuncioService;

    // Endpoint para crear un nuevo anuncio
    @PostMapping("/crear")
    public ResponseEntity<Anuncio> createAnuncio(@Valid @RequestBody Anuncio anuncio) {
        Anuncio newAnuncio = anuncioService.createAnuncio(anuncio);
        return new ResponseEntity<>(newAnuncio, HttpStatus.CREATED);
    }

    // Endpoint para obtener todos los anuncios
    @GetMapping("/lista")
    public ResponseEntity<List<Anuncio>> getListaAnuncios() {
        List<Anuncio> anuncioList = anuncioService.getAnuncios();

        if (anuncioList.isEmpty()) {
            return new ResponseEntity<>(anuncioList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(anuncioList, HttpStatus.OK);
        }
    }

    // Endpoint para obtener un anuncio por su ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getAnuncioById(@PathVariable Integer id) {
        Optional<Anuncio> optionalAnuncio = anuncioService.getAnuncioById(id);

        return optionalAnuncio
                .map(anuncio -> new ResponseEntity<>(anuncio, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para obtener los anuncios por el ID de la clase
    @GetMapping("/clase/{id}")
    public ResponseEntity<List<Anuncio>> getAnunciosByClaseId(@PathVariable Integer claseId) {
        List<Anuncio> anuncioList = anuncioService.getAnunciosByClaseId(claseId);

        if (anuncioList.isEmpty()) {
            return new ResponseEntity<>(anuncioList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(anuncioList, HttpStatus.OK);
        }
    }

    // Endpoint para actualizar un anuncio
    @PutMapping("/editar/{id}")
    public ResponseEntity<Anuncio> updateAnuncio(@PathVariable Integer id, @Valid @RequestBody Anuncio updatedAnuncio) {
        Optional<Anuncio> optionalAnuncio = anuncioService.getAnuncioById(id);

        return optionalAnuncio
                .map(anuncio -> new ResponseEntity<>(anuncioService.updateAnuncio(anuncio, updatedAnuncio), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para eliminar un anuncio
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Anuncio> deleteAnuncio(@PathVariable Integer id) {
        Optional<Anuncio> optionalAnuncio = anuncioService.getAnuncioById(id);

        if (optionalAnuncio.isPresent()) {
            anuncioService.deleteAnuncio(optionalAnuncio.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
