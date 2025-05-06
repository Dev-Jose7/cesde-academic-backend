package org.cesde.academic.service.impl;

import org.cesde.academic.model.ClaseHorario;
import org.cesde.academic.model.ClaseHorarioId;
import org.cesde.academic.repository.ClaseHorarioRepository;
import org.cesde.academic.service.IClaseHorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaseHorarioServiceImpl implements IClaseHorarioService {

    @Autowired
    private ClaseHorarioRepository claseHorarioRepository;

    @Override
    public ClaseHorario createClaseHorario(ClaseHorario claseHorario) {
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
        claseHorarioUpdated.setClase(claseHorario.getClase());
        claseHorarioUpdated.setHorario(claseHorario.getHorario());
        return claseHorarioRepository.save(claseHorarioUpdated); // Esto hará un UPDATE
    }

    @Override
    public void deleteClaseHorario(ClaseHorario claseHorario) {
        claseHorarioRepository.delete(claseHorario);
    }
}
