package org.cesde.academic.service;

import org.cesde.academic.model.Grupo;

import java.util.List;
import java.util.Optional;

public interface IGrupoService {

    Grupo createGrupo(Grupo grupo);
    List<Grupo> getGrupos();
    Optional<Grupo> getGrupoById(Integer id);
    List<Grupo> getGruposByProgramaId(Integer programaId);
    List<Grupo> getGruposBySemestreId(Integer semestreId);
    Grupo updateGrupo(Grupo grupo, Grupo grupoUpdated);
    void deleteGrupo(Grupo grupo);
}
