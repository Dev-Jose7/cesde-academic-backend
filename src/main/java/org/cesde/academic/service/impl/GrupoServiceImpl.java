package org.cesde.academic.service.impl;

import org.cesde.academic.model.Grupo;
import org.cesde.academic.repository.GrupoRepository;
import org.cesde.academic.service.IGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoServiceImpl implements IGrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    @Override
    public Grupo createGrupo(Grupo grupo) {
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
        grupoUpdated.setId(grupo.getId());
        return grupoRepository.save(grupoUpdated);
    }

    @Override
    public void deleteGrupo(Grupo grupo) {
        grupoRepository.delete(grupo);
    }
}
