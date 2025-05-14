package org.cesde.academic.service;

import org.cesde.academic.dto.request.ReporteRequestDTO;
import org.cesde.academic.dto.response.ReporteResponseDTO;
import org.cesde.academic.enums.EstadoReporte;

import java.time.LocalDate;
import java.util.List;

public interface IReporteService {
    ReporteResponseDTO createReporte(ReporteRequestDTO request);
    List<ReporteResponseDTO> getReportes();
    ReporteResponseDTO getReporteById(Integer id);
    List<ReporteResponseDTO> getReportesByClaseId(Integer claseId);
    List<ReporteResponseDTO> getReportesByUsuarioId(Integer usuarioId);
    List<ReporteResponseDTO> getReportesByFecha(LocalDate fecha);
    List<ReporteResponseDTO> getReportesByEstado(EstadoReporte estado);
    ReporteResponseDTO updateReporte(Integer id, ReporteRequestDTO request);
    void deleteReporte(Integer id);
}
