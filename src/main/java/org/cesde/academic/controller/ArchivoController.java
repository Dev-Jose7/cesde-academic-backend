package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.model.Archivo;
import org.cesde.academic.service.IArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/archivo")
public class ArchivoController {

    @Autowired
    private IArchivoService archivoService;

    // Endpoint para crear un nuevo archivo
    @PostMapping("/crear")
    public ResponseEntity<Archivo> createArchivo(@Valid @RequestBody Archivo archivo) {
        Archivo newArchivo = archivoService.createArchivo(archivo);
        return new ResponseEntity<>(newArchivo, HttpStatus.CREATED);
    }

    // Endpoint para obtener todos los archivos
    @GetMapping("/lista")
    public ResponseEntity<List<Archivo>> getListaArchivos() {
        List<Archivo> archivoList = archivoService.getArchivos();

        if (archivoList.isEmpty()) {
            return new ResponseEntity<>(archivoList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(archivoList, HttpStatus.OK);
        }
    }

    // Endpoint para obtener un archivo por su ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getArchivoById(@PathVariable Integer id) {
        Optional<Archivo> optionalArchivo = archivoService.getArchivoById(id);

        return optionalArchivo
                .map(archivo -> new ResponseEntity<>(archivo, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para obtener los archivos por ID de clase
    @GetMapping("/clase/{id}")
    public ResponseEntity<List<Archivo>> getArchivosByClaseId(@PathVariable Integer claseId) {
        List<Archivo> archivoList = archivoService.getArchivosByClaseId(claseId);

        if (archivoList.isEmpty()) {
            return new ResponseEntity<>(archivoList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(archivoList, HttpStatus.OK);
        }
    }

    // Endpoint para obtener los archivos por ID de usuario
    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Archivo>> getArchivosByUsuarioId(@PathVariable Integer usuarioId) {
        List<Archivo> archivoList = archivoService.getArchivosByUsuarioId(usuarioId);

        if (archivoList.isEmpty()) {
            return new ResponseEntity<>(archivoList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(archivoList, HttpStatus.OK);
        }
    }

    // Endpoint para actualizar un archivo
    @PutMapping("/editar/{id}")
    public ResponseEntity<Archivo> updateArchivo(@PathVariable Integer id, @Valid @RequestBody Archivo updatedArchivo) {
        Optional<Archivo> optionalArchivo = archivoService.getArchivoById(id);

        return optionalArchivo
                .map(archivo -> new ResponseEntity<>(archivoService.updateArchivo(archivo, updatedArchivo), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para eliminar un archivo
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Archivo> deleteArchivo(@PathVariable Integer id) {
        Optional<Archivo> optionalArchivo = archivoService.getArchivoById(id);

        if (optionalArchivo.isPresent()) {
            archivoService.deleteArchivo(optionalArchivo.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
