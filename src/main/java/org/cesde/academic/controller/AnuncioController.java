package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.dto.request.AnuncioRequestDTO;
import org.cesde.academic.dto.response.AnuncioResponseDTO;
import org.cesde.academic.service.IAnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/anuncio")
public class AnuncioController {

    @Autowired
    private IAnuncioService anuncioService;

    @PostMapping("/crear")
    public ResponseEntity<AnuncioResponseDTO> createAnuncio(@Valid @RequestBody AnuncioRequestDTO request) {
        return new ResponseEntity<>(anuncioService.createAnuncio(request), HttpStatus.CREATED);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<AnuncioResponseDTO>> getAnuncios() {
        List<AnuncioResponseDTO> anuncios = anuncioService.getAnuncios();
        return anuncios.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(anuncios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnuncioResponseDTO> getAnuncioById(@PathVariable Integer id) {
        return new ResponseEntity<>(anuncioService.getAnuncioById(id), HttpStatus.OK);
    }

    @GetMapping("/clase/{id}")
    public ResponseEntity<List<AnuncioResponseDTO>> getAnunciosByClaseId(@PathVariable Integer id) {
        List<AnuncioResponseDTO> anuncios = anuncioService.getAnunciosByClaseId(id);
        return anuncios.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(anuncios, HttpStatus.OK);
    }

    @GetMapping("/buscar/titulo/{titulo}")
    public ResponseEntity<List<AnuncioResponseDTO>> getAnunciosByTitulo(@PathVariable String titulo) {
        List<AnuncioResponseDTO> anuncios = anuncioService.getAnunciosByTitulo(titulo);
        return anuncios.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(anuncios, HttpStatus.OK);
    }

    @GetMapping("/buscar/fecha")
    public ResponseEntity<List<AnuncioResponseDTO>> getAnunciosByFechaRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime hasta) {
        List<AnuncioResponseDTO> anuncios = anuncioService.getAnunciosByFechaRange(desde, hasta);
        return anuncios.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(anuncios, HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<AnuncioResponseDTO> updateAnuncio(@PathVariable Integer id,
                                                            @Valid @RequestBody AnuncioRequestDTO request) {
        return new ResponseEntity<>(anuncioService.updateAnuncio(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> deleteAnuncio(@PathVariable Integer id) {
        anuncioService.deleteAnuncio(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
