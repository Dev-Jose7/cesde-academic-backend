package org.cesde.academic.service;

import org.cesde.academic.model.Calificacion;

import java.util.List;
import java.util.Optional;

public interface ICalificacionService {

    Calificacion createCalificacion(Calificacion calificacion);

    List<Calificacion> getCalificaciones();
    Optional<Calificacion> getCalificacionById(Integer id);
    List<Calificacion> getCalificacionesByActividadId(Integer actividadId);
    List<Calificacion> getCalificacionesByEstudianteId(Integer estudianteId);
    Calificacion updateCalificacion(Calificacion calificacion, Calificacion calificacionUpdated);
    void deleteCalificacion(Calificacion calificacion);
}
