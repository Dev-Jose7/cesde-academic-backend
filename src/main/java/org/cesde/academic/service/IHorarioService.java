package org.cesde.academic.service;

import org.cesde.academic.dto.request.HorarioRequestDTO;
import org.cesde.academic.dto.response.HorarioResponseDTO;

import java.util.List;

public interface IHorarioService {
    HorarioResponseDTO createHorario(HorarioRequestDTO request);
    HorarioResponseDTO getHorarioById(Integer id);
    List<HorarioResponseDTO> getHorarios();
    List<HorarioResponseDTO> getHorariosByDiaId(Integer diaId);
    List<HorarioResponseDTO> getHorariosByFranjaHorariaId(Integer franjaId);
    HorarioResponseDTO updateHorario(Integer id, HorarioRequestDTO request);
    void deleteHorario(Integer id);
}
