package org.cesde.academic.service;

import org.cesde.academic.dto.request.ClaseHorarioRequestDTO;
import org.cesde.academic.dto.response.ClaseHorarioResponseDTO;
import org.cesde.academic.model.ClaseHorario;
import org.cesde.academic.model.ClaseHorarioId;

import java.util.List;

public interface IClaseHorarioService {

    ClaseHorarioResponseDTO createClaseHorario(ClaseHorarioRequestDTO request);
    List<ClaseHorarioResponseDTO> getClaseHorarios();
    ClaseHorarioResponseDTO getClaseHorarioById(ClaseHorarioId id);
    List<ClaseHorarioResponseDTO> getClaseHorariosByClaseId(Integer id);
    List<ClaseHorarioResponseDTO> getClaseHorariosByHorarioId(Integer id);
    ClaseHorarioResponseDTO updateClaseHorario(ClaseHorarioId id, ClaseHorarioRequestDTO request);
    void deleteClaseHorario(ClaseHorarioId id);
}
