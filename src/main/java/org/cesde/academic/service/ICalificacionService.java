package org.cesde.academic.service;

import org.cesde.academic.dto.request.CalificacionRequestDTO;
import org.cesde.academic.dto.response.CalificacionResponseDTO;

import java.util.List;

public interface ICalificacionService {
    CalificacionResponseDTO createCalificacion(CalificacionRequestDTO request);
    List<CalificacionResponseDTO> getCalificaciones();
    CalificacionResponseDTO getCalificacionById(Integer id);
    List<CalificacionResponseDTO> getCalificacionesByActividad(Integer actividadId);
    List<CalificacionResponseDTO> getCalificacionesByEstudiante(Integer estudianteId);
    CalificacionResponseDTO updateCalificacion(Integer id, CalificacionRequestDTO request);
    void deleteCalificacion(Integer id);
}
