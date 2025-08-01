package org.cesde.academic.service;

import org.cesde.academic.dto.request.ProgramaRequestDTO;
import org.cesde.academic.dto.response.ProgramaResponseDTO;

import java.util.List;

public interface IProgramaService {

    ProgramaResponseDTO createPrograma(ProgramaRequestDTO request);
    List<ProgramaResponseDTO> getProgramas();
    ProgramaResponseDTO getProgramaById(Integer id);
    List<ProgramaResponseDTO> getProgramasByNombre(String nombre);
    List<ProgramaResponseDTO> getProgramasByEscuelaId(Integer escuelaId);
    ProgramaResponseDTO updatePrograma(Integer id, ProgramaRequestDTO request);
    void deletePrograma(Integer id);

    // Incluye los métodos CRUD comunes.
    // Añade getProgramasByEscuelaId, que corresponde al contrato personalizado findByEscuela_Id de su repositorio
    // (ProgramaRepository).
}
