package org.cesde.academic.service;

import org.cesde.academic.model.GrupoEstudiante;
import org.cesde.academic.model.GrupoEstudianteId;

import java.util.List;
import java.util.Optional;

public interface IGrupoEstudianteService {

    GrupoEstudiante createGrupoEstudiante(GrupoEstudiante grupoEstudiante);
    List<GrupoEstudiante> getGrupoEstudiantes();
    // Modificamos para que reciba el ID compuesto como un objeto de tipo GrupoEstudianteId
    Optional<GrupoEstudiante> getGrupoEstudianteById(GrupoEstudianteId grupoEstudianteId);
    List<GrupoEstudiante> getGrupoEstudiantesByGrupoId(Integer grupoId);
    List<GrupoEstudiante> getGrupoEstudiantesByEstudianteId(Integer estudianteId);
    GrupoEstudiante updateGrupoEstudiante(GrupoEstudiante grupoEstudiante, GrupoEstudiante grupoEstudianteUpdated);
    void deleteGrupoEstudiante(GrupoEstudiante grupoEstudiante);
}

