package org.cesde.academic.service;

import org.cesde.academic.dto.request.GrupoRequestDTO;
import org.cesde.academic.dto.response.GrupoResponseDTO;

import java.util.List;

public interface IGrupoService {
    GrupoResponseDTO createGrupo(GrupoRequestDTO request);
    List<GrupoResponseDTO> getGrupos();
    GrupoResponseDTO getGrupoById(Integer id);
    List<GrupoResponseDTO> getGruposByCodigo(String codigo);
    List<GrupoResponseDTO> getGruposByProgramaId(Integer programaId);
    List<GrupoResponseDTO> getGruposBySemestreId(Integer semestreId);
    GrupoResponseDTO updateGrupo(Integer id, GrupoRequestDTO request);
    void deleteGrupo(Integer id);
}
