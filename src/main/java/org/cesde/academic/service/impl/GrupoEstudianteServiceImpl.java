package org.cesde.academic.service.impl;

import org.cesde.academic.model.GrupoEstudiante;
import org.cesde.academic.model.GrupoEstudianteId;
import org.cesde.academic.repository.GrupoEstudianteRepository;
import org.cesde.academic.service.IGrupoEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoEstudianteServiceImpl implements IGrupoEstudianteService {

    @Autowired
    private GrupoEstudianteRepository grupoEstudianteRepository;

    @Override
    public GrupoEstudiante createGrupoEstudiante(GrupoEstudiante grupoEstudiante) {
        return grupoEstudianteRepository.save(grupoEstudiante);
    }

    @Override
    public List<GrupoEstudiante> getGrupoEstudiantes() {
        return grupoEstudianteRepository.findAll();
    }

    @Override
    public Optional<GrupoEstudiante> getGrupoEstudianteById(GrupoEstudianteId grupoEstudianteId) {
        return grupoEstudianteRepository.findById(grupoEstudianteId); // Directamente usamos el ID compuesto
    }

    @Override
    public List<GrupoEstudiante> getGrupoEstudiantesByGrupoId(Integer grupoId) {
        return grupoEstudianteRepository.findByGrupo_Id(grupoId);
    }

    @Override
    public List<GrupoEstudiante> getGrupoEstudiantesByEstudianteId(Integer estudianteId) {
        return grupoEstudianteRepository.findByEstudiante_Id(estudianteId);
    }

    @Override
    public GrupoEstudiante updateGrupoEstudiante(GrupoEstudiante grupoEstudiante, GrupoEstudiante grupoEstudianteUpdated) {
        grupoEstudianteUpdated.setGrupo(grupoEstudiante.getGrupo());  // En este caso se asume que grupo no cambia
        grupoEstudianteUpdated.setEstudiante(grupoEstudiante.getEstudiante()); // Lo mismo con estudiante
        return grupoEstudianteRepository.save(grupoEstudianteUpdated);
    }

    @Override
    public void deleteGrupoEstudiante(GrupoEstudiante grupoEstudiante) {
        grupoEstudianteRepository.delete(grupoEstudiante);
    }
}

