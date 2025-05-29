package org.cesde.academic.service;

import org.cesde.academic.dto.request.PensumRequestDTO;
import org.cesde.academic.dto.response.PensumResponseDTO;
import org.cesde.academic.enums.NivelPensum;

import java.util.List;

public interface IPensumService {
    PensumResponseDTO createPensum(PensumRequestDTO request);
    List<PensumResponseDTO> getPensums();
    PensumResponseDTO getPensuById(Integer id);
    List<PensumResponseDTO> getPensumByProgramaId(Integer id);
    List<PensumResponseDTO> getPensumByModuloId(Integer id);
    List<PensumResponseDTO> getPensumByNivel(NivelPensum nivel);
    PensumResponseDTO updatePensum(Integer id, PensumRequestDTO request);
    void deletePensum(Integer id);
}
