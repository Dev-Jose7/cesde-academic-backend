package org.cesde.academic.service.impl;

import org.cesde.academic.model.Calificacion;
import org.cesde.academic.repository.CalificacionRepository;
import org.cesde.academic.service.ICalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalificacionServiceImpl implements ICalificacionService {

    @Autowired
    private CalificacionRepository calificacionRepository;

    @Override
    public Calificacion createCalificacion(Calificacion calificacion) {
        return calificacionRepository.save(calificacion);
    }

    @Override
    public List<Calificacion> getCalificaciones() {
        return calificacionRepository.findAll();
    }

    @Override
    public Optional<Calificacion> getCalificacionById(Integer id) {
        return calificacionRepository.findById(id);
    }

    @Override
    public List<Calificacion> getCalificacionesByActividadId(Integer actividadId) {
        return calificacionRepository.findByActividad_Id(actividadId);
    }

    @Override
    public List<Calificacion> getCalificacionesByEstudianteId(Integer estudianteId) {
        return calificacionRepository.findByEstudiante_Id(estudianteId);
    }

    @Override
    public Calificacion updateCalificacion(Calificacion calificacion, Calificacion calificacionUpdated) {
        calificacionUpdated.setId(calificacion.getId()); // Aquí asignamos el id de la entidad existente.
        return calificacionRepository.save(calificacionUpdated); // Esto ahora realiza un UPDATE en lugar de un INSERT.
    }

    @Override
    public void deleteCalificacion(Calificacion calificacion) {
        calificacionRepository.delete(calificacion);
    }
}
