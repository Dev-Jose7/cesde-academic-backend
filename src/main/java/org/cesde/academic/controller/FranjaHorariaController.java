package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.dto.request.FranjaHorariaRequestDTO;
import org.cesde.academic.dto.response.FranjaHorariaResponseDTO;
import org.cesde.academic.service.IFranjaHorariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/franja-horaria")
public class FranjaHorariaController {

    @Autowired
    private IFranjaHorariaService service;

    @PostMapping("/crear")
    public ResponseEntity<FranjaHorariaResponseDTO> create(@Valid @RequestBody FranjaHorariaRequestDTO request) {
        return new ResponseEntity<>(service.createFranjaHoraria(request), HttpStatus.CREATED);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<FranjaHorariaResponseDTO>> getAll() {
        List<FranjaHorariaResponseDTO> franjas = service.getFranjasHorarias();
        return franjas.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(franjas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FranjaHorariaResponseDTO> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(service.getFranjaHorariaById(id), HttpStatus.OK);
    }

    @GetMapping("/inicio")
    public ResponseEntity<List<FranjaHorariaResponseDTO>> getByHoraInicio(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime horaInicio) {
        return new ResponseEntity<>(service.getFranjasByHoraInicio(horaInicio), HttpStatus.OK);
    }

    @GetMapping("/fin")
    public ResponseEntity<List<FranjaHorariaResponseDTO>> getByHoraFin(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime horaFin) {
        return new ResponseEntity<>(service.getFranjasByHoraFin(horaFin), HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<FranjaHorariaResponseDTO> update(@PathVariable Integer id,
                                                           @Valid @RequestBody FranjaHorariaRequestDTO request) {
        return new ResponseEntity<>(service.updateFranjaHoraria(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteFranjaHoraria(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
