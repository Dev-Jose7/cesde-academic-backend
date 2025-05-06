package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.model.Reporte;
import org.cesde.academic.service.IReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reporte")
public class ReporteController {

    @Autowired
    private IReporteService reporteService;

    // Endpoint para crear un nuevo reporte
    @PostMapping("/crear")
    public ResponseEntity<Reporte> createReporte(@Valid @RequestBody Reporte reporte) {
        Reporte newReporte = reporteService.createReporte(reporte);
        return new ResponseEntity<>(newReporte, HttpStatus.CREATED);
    }

    // Endpoint para obtener todos los reportes
    @GetMapping("/lista")
    public ResponseEntity<List<Reporte>> getListaReportes() {
        List<Reporte> reporteList = reporteService.getReportes();

        if (reporteList.isEmpty()) {
            return new ResponseEntity<>(reporteList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(reporteList, HttpStatus.OK);
        }
    }

    // Endpoint para obtener un reporte por su ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getReporteById(@PathVariable Integer id) {
        Optional<Reporte> optionalReporte = reporteService.getReporteById(id);

        return optionalReporte
                .map(reporte -> new ResponseEntity<>(reporte, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para obtener los reportes por el ID de la clase
    @GetMapping("/clase/{id}")
    public ResponseEntity<List<Reporte>> getReportesByClaseId(@PathVariable Integer claseId) {
        List<Reporte> reporteList = reporteService.getReportesByClaseId(claseId);

        if (reporteList.isEmpty()) {
            return new ResponseEntity<>(reporteList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(reporteList, HttpStatus.OK);
        }
    }

    // Endpoint para obtener los reportes por el ID del usuario
    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Reporte>> getReportesByUsuarioId(@PathVariable Integer usuarioId) {
        List<Reporte> reporteList = reporteService.getReportesByUsuarioId(usuarioId);

        if (reporteList.isEmpty()) {
            return new ResponseEntity<>(reporteList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(reporteList, HttpStatus.OK);
        }
    }

    // Endpoint para actualizar un reporte
    @PutMapping("/editar/{id}")
    public ResponseEntity<Reporte> updateReporte(@PathVariable Integer id, @Valid @RequestBody Reporte updatedReporte) {
        Optional<Reporte> optionalReporte = reporteService.getReporteById(id);

        return optionalReporte
                .map(reporte -> new ResponseEntity<>(reporteService.updateReporte(reporte, updatedReporte), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para eliminar un reporte
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Reporte> deleteReporte(@PathVariable Integer id) {
        Optional<Reporte> optionalReporte = reporteService.getReporteById(id);

        if (optionalReporte.isPresent()) {
            reporteService.deleteReporte(optionalReporte.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
