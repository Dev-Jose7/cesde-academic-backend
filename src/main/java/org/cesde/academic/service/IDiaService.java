package org.cesde.academic.service;

import org.cesde.academic.dto.request.DiaRequestDTO;
import org.cesde.academic.dto.response.DiaResponseDTO;
import org.cesde.academic.enums.NombreDia;

import java.util.List;

public interface IDiaService {
    DiaResponseDTO createDia(DiaRequestDTO request);
    List<DiaResponseDTO> getDias();
    DiaResponseDTO getDiaById(Integer id);
    DiaResponseDTO getDiaByNombre(NombreDia nombre);
    DiaResponseDTO updateDia(Integer id, DiaRequestDTO request);
    void deleteDia(Integer id);
}
