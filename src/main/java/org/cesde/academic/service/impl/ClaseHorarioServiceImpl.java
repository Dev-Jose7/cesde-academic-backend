package org.cesde.academic.service.impl;

import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.model.Clase;
import org.cesde.academic.model.ClaseHorario;
import org.cesde.academic.model.ClaseHorarioId;
import org.cesde.academic.model.Horario;
import org.cesde.academic.repository.ClaseHorarioRepository;
import org.cesde.academic.repository.ClaseRepository;
import org.cesde.academic.repository.HorarioRepository;
import org.cesde.academic.service.IClaseHorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaseHorarioServiceImpl implements IClaseHorarioService {

    @Autowired
    private ClaseHorarioRepository claseHorarioRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private ClaseRepository claseRepository;

    @Override
    public ClaseHorario createClaseHorario(ClaseHorario claseHorario) {
        Clase clase = claseRepository.findById(claseHorario.getClase().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Clase no existente"));

        Horario horario = horarioRepository.findById(claseHorario.getHorario().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Clase no existente"));

        ClaseHorarioId id = new ClaseHorarioId(clase.getId(), horario.getId());
        if (claseHorarioRepository.existsById(id)) {
            throw new IllegalStateException("Esta relación ya existe");
        }

        claseHorario.setClase(clase);
        claseHorario.setHorario(horario);
        return claseHorarioRepository.save(claseHorario);
    }

    @Override
    public List<ClaseHorario> getClaseHorarios() {
        return claseHorarioRepository.findAll();
    }

    @Override
    public Optional<ClaseHorario> getClaseHorarioById(ClaseHorarioId claseHorarioId) {
        return claseHorarioRepository.findById(claseHorarioId);
    }

    @Override
    public List<ClaseHorario> getClaseHorariosByClaseId(Integer claseId) {
        return claseHorarioRepository.findByClase_Id(claseId);
    }

    @Override
    public List<ClaseHorario> getClaseHorariosByHorarioId(Integer horarioId) {
        return claseHorarioRepository.findByHorario_Id(horarioId);
    }

    @Override
    public ClaseHorario updateClaseHorario(ClaseHorario claseHorario, ClaseHorario claseHorarioUpdated) {

        Clase clase = claseRepository.findById(claseHorarioUpdated.getHorario().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Clase no existente"));

        Horario horario = horarioRepository.findById(claseHorarioUpdated.getHorario().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Clase no existente"));

        ClaseHorarioId id = new ClaseHorarioId(clase.getId(), horario.getId());
        if (claseHorarioRepository.existsById(id)) {
            throw new IllegalStateException("Esta relación ya existe");
        }

        claseHorarioRepository.delete(claseHorario);

        claseHorarioUpdated.setClase(clase);
        claseHorarioUpdated.setHorario(horario);
        return claseHorarioRepository.save(claseHorarioUpdated); // Esto hará un UPDATE
    }

    @Override
    public void deleteClaseHorario(ClaseHorario claseHorario) {
        claseHorarioRepository.delete(claseHorario);
    }
}
