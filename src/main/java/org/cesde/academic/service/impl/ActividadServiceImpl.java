package org.cesde.academic.service.impl;

import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.model.Actividad;
import org.cesde.academic.model.Clase;
import org.cesde.academic.repository.ActividadRepository;
import org.cesde.academic.repository.ClaseRepository;
import org.cesde.academic.service.IActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActividadServiceImpl implements IActividadService {

    @Autowired
    private ActividadRepository actividadRepository;

    @Autowired
    private ClaseRepository claseRepository;

    @Override
    public Actividad createActividad(Actividad actividad) {
        Clase clase = claseRepository.findById(actividad.getClase().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Clase no existente"));

        actividad.setClase(clase);
        return actividadRepository.save(actividad);
    }

    @Override
    public List<Actividad> getActividades() {
        return actividadRepository.findAll();
    }

    @Override
    public Optional<Actividad> getActividadById(Integer id) {
        return actividadRepository.findById(id);
    }

    @Override
    public List<Actividad> getActividadesByClaseId(Integer claseId) {
        return actividadRepository.findByClase_Id(claseId);
    }

    @Override
    public Actividad updateActividad(Actividad actividad, Actividad actividadUpdated) {
        Clase clase = claseRepository.findById(actividadUpdated.getClase().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Clase no existente"));

        actividadUpdated.setId(actividad.getId());
        actividadUpdated.setClase(clase);
        return actividadRepository.save(actividadUpdated);
    }

    @Override
    public void deleteActividad(Actividad actividad) {
        actividadRepository.delete(actividad);
    }
}
