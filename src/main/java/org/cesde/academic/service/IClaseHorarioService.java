package org.cesde.academic.service;

import org.cesde.academic.model.ClaseHorario;

import java.util.List;
import java.util.Optional;

public interface IClaseHorarioService {

    ClaseHorario createClaseHorario(ClaseHorario claseHorario);
    List<ClaseHorario> getClaseHorarios();
    Optional<ClaseHorario> getClaseHorarioById(Integer id);
    List<ClaseHorario> getClaseHorariosByClaseId(Integer claseId);
    List<ClaseHorario> getClaseHorariosByHorarioId(Integer horarioId);
    ClaseHorario updateClaseHorario(ClaseHorario claseHorario, ClaseHorario claseHorarioUpdated);
    void deleteClaseHorario(ClaseHorario claseHorario);
}
