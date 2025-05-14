package org.cesde.academic.service;

import org.cesde.academic.dto.request.AsistenciaRequestDTO;
import org.cesde.academic.dto.response.AsistenciaResponseDTO;
import org.cesde.academic.enums.EstadoAsistencia;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IAsistenciaService {
    AsistenciaResponseDTO createAsistencia(AsistenciaRequestDTO request);
    List<AsistenciaResponseDTO> getAsistencias();
    AsistenciaResponseDTO getAsistenciaById(Integer id);
    List<AsistenciaResponseDTO> getAsistenciasByClaseId(Integer claseId);
    List<AsistenciaResponseDTO> getAsistenciasByEstudianteId(Integer estudianteId);
    List<AsistenciaResponseDTO> getAsistenciasByFechaRange(LocalDate desde, LocalDate hasta);
    List<AsistenciaResponseDTO> getAsistenciasByEstado(EstadoAsistencia estado);
    AsistenciaResponseDTO updateAsistencia(Integer id, AsistenciaRequestDTO request);
    void deleteAsistencia(Integer id);
}
