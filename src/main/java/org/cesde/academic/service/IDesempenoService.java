package org.cesde.academic.service;

import org.cesde.academic.dto.request.DesempenoRequestDTO;
import org.cesde.academic.dto.response.DesempenoResponseDTO;
import org.cesde.academic.enums.EstadoDesempeno;

import java.util.List;

public interface IDesempenoService {
    DesempenoResponseDTO createDesempeno(DesempenoRequestDTO request);
    List<DesempenoResponseDTO> getDesempenos();
    DesempenoResponseDTO getDesempenoById(Integer id);
    List<DesempenoResponseDTO> getDesempenoByEstudiante(Integer id);
    List<DesempenoResponseDTO> getDesempenoByModulo(Integer id);
    List<DesempenoResponseDTO> getDesempenoByEstado(EstadoDesempeno estado);
    DesempenoResponseDTO updateDesempeno(Integer id, DesempenoRequestDTO request);
    void deleteDesempeno(Integer id);
}
