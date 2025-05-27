package org.cesde.academic.controller;

import jakarta.validation.Valid;
import org.cesde.academic.dto.request.HorarioRequestDTO;
import org.cesde.academic.dto.response.HorarioResponseDTO;
import org.cesde.academic.service.IHorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/horario")
public class HorarioController {

    @Autowired
    private IHorarioService horarioService;

    @PostMapping("/crear")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_DEV') and hasAuthority('CREATE')")
    public ResponseEntity<HorarioResponseDTO> crear(@Valid @RequestBody HorarioRequestDTO request) {
        return ResponseEntity.status(201).body(horarioService.createHorario(request));
    }

    @GetMapping("/lista")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<HorarioResponseDTO>> listar() {
        List<HorarioResponseDTO> lista = horarioService.getHorarios();
        return lista.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<HorarioResponseDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(horarioService.getHorarioById(id));
    }

    @GetMapping("/dia/{diaId}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<HorarioResponseDTO>> obtenerPorDia(@PathVariable Integer diaId) {
        List<HorarioResponseDTO> lista = horarioService.getHorariosByDiaId(diaId);
        return lista.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(lista);
    }

    @GetMapping("/franja/{franjaId}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_DEV') and hasAuthority('READ')")
    public ResponseEntity<List<HorarioResponseDTO>> obtenerPorFranja(@PathVariable Integer franjaId) {
        List<HorarioResponseDTO> lista = horarioService.getHorariosByFranjaHorariaId(franjaId);
        return lista.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(lista);
    }

    @PutMapping("/editar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVO') or hasRole('ROLE_DEV') and hasAuthority('UPDATE')")
    public ResponseEntity<HorarioResponseDTO> editar(@PathVariable Integer id, @Valid @RequestBody HorarioRequestDTO request) {
        return ResponseEntity.ok(horarioService.updateHorario(id, request));
    }

    @DeleteMapping("/remover/{id}")
    @PreAuthorize("hasRole('ROLE_DEV') and hasAuthority('DELETE')")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        horarioService.deleteHorario(id);
        return ResponseEntity.noContent().build();
    }
}
