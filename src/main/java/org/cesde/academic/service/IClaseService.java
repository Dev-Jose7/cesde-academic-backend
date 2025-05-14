package org.cesde.academic.service;

import org.cesde.academic.dto.request.ClaseRequestDTO;
import org.cesde.academic.dto.response.ClaseResponseDTO;

import java.util.List;

public interface IClaseService {
    ClaseResponseDTO createClase(ClaseRequestDTO request);
    List<ClaseResponseDTO> getClases();
    ClaseResponseDTO getClaseById(Integer id);
    List<ClaseResponseDTO> getClasesByGrupo(Integer grupoId);
    List<ClaseResponseDTO> getClasesByDocente(Integer docenteId);
    List<ClaseResponseDTO> getClasesByModulo(Integer moduloId);
    ClaseResponseDTO updateClase(Integer id, ClaseRequestDTO request);
    void deleteClase(Integer id);
}
