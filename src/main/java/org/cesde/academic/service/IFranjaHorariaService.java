package org.cesde.academic.service;

import org.cesde.academic.dto.request.FranjaHorariaRequestDTO;
import org.cesde.academic.dto.response.FranjaHorariaResponseDTO;

import java.time.LocalTime;
import java.util.List;

public interface IFranjaHorariaService {
    FranjaHorariaResponseDTO createFranjaHoraria(FranjaHorariaRequestDTO request);
    List<FranjaHorariaResponseDTO> getFranjasHorarias();
    FranjaHorariaResponseDTO getFranjaHorariaById(Integer id);
    List<FranjaHorariaResponseDTO> getFranjasByHoraInicio(LocalTime horaInicio);
    List<FranjaHorariaResponseDTO> getFranjasByHoraFin(LocalTime horaFin);
    FranjaHorariaResponseDTO updateFranjaHoraria(Integer id, FranjaHorariaRequestDTO request);
    void deleteFranjaHoraria(Integer id);
}
