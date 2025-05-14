package org.cesde.academic.service;

import org.cesde.academic.dto.request.SemestreRequestDTO;
import org.cesde.academic.dto.response.SemestreResponseDTO;

import java.util.List;

public interface ISemestreService {

    SemestreResponseDTO createSemestre(SemestreRequestDTO request);
    List<SemestreResponseDTO> getSemestres();
    SemestreResponseDTO getSemestreById(Integer id);
    List<SemestreResponseDTO> getSemestresByNombre(String nombre);
    SemestreResponseDTO updateSemestre(Integer id, SemestreRequestDTO request);
    void deleteSemestre(Integer id);
}
