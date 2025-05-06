package org.cesde.academic.service;

import org.cesde.academic.model.Actividad;

import java.util.List;
import java.util.Optional;

public interface IActividadService {

    Actividad createActividad(Actividad actividad);
    List<Actividad> getActividades();
    Optional<Actividad> getActividadById(Integer id);
    List<Actividad> getActividadesByClaseId(Integer claseId);
    Actividad updateActividad(Actividad actividad, Actividad actividadUpdated);
    void deleteActividad(Actividad actividad);
}
