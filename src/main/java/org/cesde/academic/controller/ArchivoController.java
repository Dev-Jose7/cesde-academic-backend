package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.dto.request.ArchivoRequestDTO;
import org.cesde.academic.dto.response.ArchivoResponseDTO;
import org.cesde.academic.service.IArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/archivo")
public class ArchivoController {

    @Autowired
    private IArchivoService archivoService;

    @PostMapping("/crear")
    public ResponseEntity<ArchivoResponseDTO> createArchivo(@Valid @RequestBody ArchivoRequestDTO request) {
        return new ResponseEntity<>(archivoService.createArchivo(request), HttpStatus.CREATED);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<ArchivoResponseDTO>> getListaArchivos() {
        List<ArchivoResponseDTO> archivos = archivoService.getArchivos();
        return archivos.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(archivos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArchivoResponseDTO> getArchivoById(@PathVariable Integer id) {
        return new ResponseEntity<>(archivoService.getArchivoById(id), HttpStatus.OK);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<ArchivoResponseDTO>> getArchivosByUsuario(@PathVariable Integer usuarioId) {
        List<ArchivoResponseDTO> archivos = archivoService.getArchivosByUsuarioId(usuarioId);
        return archivos.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(archivos, HttpStatus.OK);
    }


    @GetMapping("/buscar/nombre/{archivo}")
    public ResponseEntity<List<ArchivoResponseDTO>> getArchivosByNombre(@PathVariable String nombreArchivo) {
        List<ArchivoResponseDTO> archivos = archivoService.getArchivosByNombreArchivo(nombreArchivo);
        return archivos.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(archivos, HttpStatus.OK);
    }

    @GetMapping("/buscar/ruta/{archivo}")
    public ResponseEntity<List<ArchivoResponseDTO>> getArchivosByRuta(@PathVariable String rutaArchivo) {
        List<ArchivoResponseDTO> archivos = archivoService.getArchivosByRutaArchivo(rutaArchivo);
        return archivos.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(archivos, HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<ArchivoResponseDTO> updateArchivo(@PathVariable Integer id, @Valid @RequestBody ArchivoRequestDTO request) {
        return new ResponseEntity<>(archivoService.updateArchivo(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> deleteArchivo(@PathVariable Integer id) {
        archivoService.deleteArchivo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
