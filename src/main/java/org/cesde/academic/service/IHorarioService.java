package org.cesde.academic.service;

import org.cesde.academic.model.Horario;

import java.util.List;
import java.util.Optional;

public interface IHorarioService {

    Horario createHorario(Horario horario);
    List<Horario> getHorarios();
    Optional<Horario> getHorarioById(Integer id);
    List<Horario> getHorariosByDiaId(Integer diaId);
    List<Horario> getHorariosByFranjaId(Integer franjaId);
    Horario updateHorario(Horario horario, Horario horarioUpdated);
    void deleteHorario(Horario horario);
}
