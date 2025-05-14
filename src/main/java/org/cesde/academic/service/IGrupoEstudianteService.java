package org.cesde.academic.service;

import org.cesde.academic.dto.request.GrupoEstudianteRequestDTO;
import org.cesde.academic.dto.response.GrupoEstudianteResponseDTO;
import org.cesde.academic.model.GrupoEstudiante;
import org.cesde.academic.model.GrupoEstudianteId;

import java.util.List;

public interface IGrupoEstudianteService {

    GrupoEstudianteResponseDTO createGrupoEstudiante(GrupoEstudianteRequestDTO request);
    List<GrupoEstudianteResponseDTO> getGrupoEstudiantes();
    GrupoEstudianteResponseDTO getGrupoEstudianteById(GrupoEstudianteId id);
    List<GrupoEstudianteResponseDTO> getGrupoEstudiantesByGrupoId(Integer grupoId);
    List<GrupoEstudianteResponseDTO> getGrupoEstudiantesByEstudianteId(Integer estudianteId);
    GrupoEstudianteResponseDTO updateGrupoEstudiante(GrupoEstudianteId id, GrupoEstudianteRequestDTO request);
    void deleteGrupoEstudiante(GrupoEstudianteId id);
}
