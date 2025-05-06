package org.cesde.academic.service;

import org.cesde.academic.model.Asistencia;

import java.util.List;
import java.util.Optional;

public interface IAsistenciaService {

    Asistencia createAsistencia(Asistencia asistencia);
    List<Asistencia> getAsistencias();
    Optional<Asistencia> getAsistenciaById(Integer id);
    List<Asistencia> getAsistenciasByClaseId(Integer claseId);
    List<Asistencia> getAsistenciasByEstudianteId(Integer estudianteId);
    Asistencia updateAsistencia(Asistencia asistencia, Asistencia asistenciaUpdated);
    void deleteAsistencia(Asistencia asistencia);
}
