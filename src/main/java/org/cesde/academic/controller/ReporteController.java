package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.dto.request.ReporteRequestDTO;
import org.cesde.academic.dto.response.ReporteResponseDTO;
import org.cesde.academic.enums.EstadoReporte;
import org.cesde.academic.service.IReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reporte")
public class ReporteController {

    @Autowired
    private IReporteService reporteService;

    @PostMapping("/crear")
    public ResponseEntity<ReporteResponseDTO> createReporte(@Valid @RequestBody ReporteRequestDTO request) {
        return new ResponseEntity<>(reporteService.createReporte(request), HttpStatus.CREATED);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<ReporteResponseDTO>> getReportes() {
        List<ReporteResponseDTO> reportes = reporteService.getReportes();
        return reportes.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(reportes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReporteResponseDTO> getReporteById(@PathVariable Integer id) {
        return new ResponseEntity<>(reporteService.getReporteById(id), HttpStatus.OK);
    }

    @GetMapping("/clase/{id}")
    public ResponseEntity<List<ReporteResponseDTO>> getReportesByClaseId(@PathVariable Integer id) {
        List<ReporteResponseDTO> reportes = reporteService.getReportesByClaseId(id);
        return reportes.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(reportes, HttpStatus.OK);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<ReporteResponseDTO>> getReportesByUsuarioId(@PathVariable Integer id) {
        List<ReporteResponseDTO> reportes = reporteService.getReportesByUsuarioId(id);
        return reportes.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(reportes, HttpStatus.OK);
    }

    @GetMapping("/buscar/fecha")
    public ResponseEntity<List<ReporteResponseDTO>> getReportesByFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        List<ReporteResponseDTO> reportes = reporteService.getReportesByFecha(fecha);
        return reportes.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(reportes, HttpStatus.OK);
    }

    @GetMapping("/buscar/estado")
    public ResponseEntity<List<ReporteResponseDTO>> getReportesByEstado(@RequestParam EstadoReporte estado) {
        List<ReporteResponseDTO> reportes = reporteService.getReportesByEstado(estado);
        return reportes.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(reportes, HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<ReporteResponseDTO> updateReporte(@PathVariable Integer id,
                                                            @Valid @RequestBody ReporteRequestDTO request) {
        return new ResponseEntity<>(reporteService.updateReporte(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> deleteReporte(@PathVariable Integer id) {
        reporteService.deleteReporte(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
