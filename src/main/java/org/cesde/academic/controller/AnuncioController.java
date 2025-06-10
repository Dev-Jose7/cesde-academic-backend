package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.dto.request.AnuncioRequestDTO;
import org.cesde.academic.dto.response.AnuncioResponseDTO;
import org.cesde.academic.service.IAnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/anuncio")
public class AnuncioController {

    @Autowired
    private IAnuncioService anuncioService;

    @PostMapping("/crear")
    @PreAuthorize("hasRole('ROLE_DIRECTIVO') or hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_DOCENTE') or hasRole('ROLE_DEV') and hasAuthority('CREATE')")
    public ResponseEntity<AnuncioResponseDTO> createAnuncio(@Valid @RequestBody AnuncioRequestDTO request) {
        return new ResponseEntity<>(anuncioService.createAnuncio(request), HttpStatus.CREATED);
    }

    @GetMapping("/lista")
    @PreAuthorize("hasRole('ROLE_DIRECTIVO') or hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_DOCENTE') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<AnuncioResponseDTO>> getAnuncios() {
        List<AnuncioResponseDTO> anuncios = anuncioService.getAnuncios();
        return anuncios.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(anuncios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_DIRECTIVO') or hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_DOCENTE') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<AnuncioResponseDTO> getAnuncioById(@PathVariable Integer id) {
        return new ResponseEntity<>(anuncioService.getAnuncioById(id), HttpStatus.OK);
    }

    @GetMapping("/clase/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<AnuncioResponseDTO>> getAnunciosByClaseId(@PathVariable Integer id) {
        List<AnuncioResponseDTO> anuncios = anuncioService.getAnunciosByClaseId(id);
        return anuncios.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(anuncios, HttpStatus.OK);
    }

    @GetMapping("/buscar/titulo/{titulo}")
    @PreAuthorize("hasRole('ROLE_DIRECTIVO') or hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_DOCENTE') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<AnuncioResponseDTO>> getAnunciosByTitulo(@PathVariable String titulo) {
        List<AnuncioResponseDTO> anuncios = anuncioService.getAnunciosByTitulo(titulo);
        return anuncios.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(anuncios, HttpStatus.OK);
    }

    @GetMapping("/buscar/fecha")
    @PreAuthorize("hasRole('ROLE_DIRECTIVO') or hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_DOCENTE') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<AnuncioResponseDTO>> getAnunciosByFechaRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime hasta) {
        List<AnuncioResponseDTO> anuncios = anuncioService.getAnunciosByFechaRange(desde, hasta);
        return anuncios.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(anuncios, HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    @PreAuthorize("hasRole('ROLE_DIRECTIVO') or hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_DOCENTE') or hasRole('ROLE_DEV') and hasAuthority('UPDATE')")
    public ResponseEntity<AnuncioResponseDTO> updateAnuncio(@PathVariable Integer id,
                                                            @Valid @RequestBody AnuncioRequestDTO request) {
        return new ResponseEntity<>(anuncioService.updateAnuncio(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/remover/{id}")
    @PreAuthorize("hasRole('ROLE_DOCENTE') or hasRole('ROLE_DEV') and hasAuthority('DELETE')")
    public ResponseEntity<Void> deleteAnuncio(@PathVariable Integer id) {
        anuncioService.deleteAnuncio(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
