package org.cesde.academic.service;

import org.cesde.academic.model.GrupoEstudiante;

import java.util.List;
import java.util.Optional;

public interface IGrupoEstudianteService {

    GrupoEstudiante createGrupoEstudiante(GrupoEstudiante grupoEstudiante);

    List<GrupoEstudiante> getGrupoEstudiantes();
    Optional<GrupoEstudiante> getGrupoEstudianteById(Integer id);
    List<GrupoEstudiante> getGrupoEstudiantesByGrupoId(Integer grupoId);
    List<GrupoEstudiante> getGrupoEstudiantesByEstudianteId(Integer estudianteId);
    GrupoEstudiante updateGrupoEstudiante(GrupoEstudiante grupoEstudiante, GrupoEstudiante grupoEstudianteUpdated);
    void deleteGrupoEstudiante(GrupoEstudiante grupoEstudiante);
}
