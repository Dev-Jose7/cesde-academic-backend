package org.cesde.academic.service.impl;

import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.model.Grupo;
import org.cesde.academic.model.Programa;
import org.cesde.academic.model.Semestre;
import org.cesde.academic.repository.GrupoRepository;
import org.cesde.academic.repository.ProgramaRepository;
import org.cesde.academic.repository.SemestreRepository;
import org.cesde.academic.service.IGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoServiceImpl implements IGrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private ProgramaRepository programaRepository;

    @Autowired
    private SemestreRepository semestreRepository;

    @Override
    public Grupo createGrupo(Grupo grupo) {
        Programa programa = programaRepository.findById(grupo.getPrograma().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Programa no existente"));

        Semestre semestre = semestreRepository.findById(grupo.getSemestre().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Semestre no existente"));

        grupo.setPrograma(programa);
        grupo.setSemestre(semestre);
        return grupoRepository.save(grupo);
    }

    @Override
    public List<Grupo> getGrupos() {
        return grupoRepository.findAll();
    }

    @Override
    public Optional<Grupo> getGrupoById(Integer id) {
        return grupoRepository.findById(id);
    }

    @Override
    public List<Grupo> getGruposByProgramaId(Integer programaId) {
        return grupoRepository.findByPrograma_Id(programaId);
    }

    @Override
    public List<Grupo> getGruposBySemestreId(Integer semestreId) {
        return grupoRepository.findBySemestre_Id(semestreId);
    }

    @Override
    public Grupo updateGrupo(Grupo grupo, Grupo grupoUpdated) {
        Programa programa = programaRepository.findById(grupoUpdated.getPrograma().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Programa no existente"));

        Semestre semestre = semestreRepository.findById(grupoUpdated.getSemestre().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Semestre no existente"));

        grupoUpdated.setId(grupo.getId());
        grupoUpdated.setPrograma(programa);
        grupoUpdated.setSemestre(semestre);
        return grupoRepository.save(grupoUpdated);
    }

    @Override
    public void deleteGrupo(Grupo grupo) {
        grupoRepository.delete(grupo);
    }
}
