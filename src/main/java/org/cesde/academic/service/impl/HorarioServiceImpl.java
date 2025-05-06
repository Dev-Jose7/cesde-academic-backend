package org.cesde.academic.service.impl;

import org.cesde.academic.model.Horario;
import org.cesde.academic.repository.HorarioRepository;
import org.cesde.academic.service.IHorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorarioServiceImpl implements IHorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    @Override
    public Horario createHorario(Horario horario) {
        return horarioRepository.save(horario);
    }

    @Override
    public List<Horario> getHorarios() {
        return horarioRepository.findAll();
    }

    @Override
    public Optional<Horario> getHorarioById(Integer id) {
        return horarioRepository.findById(id);
    }

    @Override
    public List<Horario> getHorariosByDiaId(Integer diaId) {
        return horarioRepository.findByDia_Id(diaId);
    }

    @Override
    public List<Horario> getHorariosByFranjaId(Integer franjaId) {
        return horarioRepository.findByfranjaHoraria_Id(franjaId);
    }

    @Override
    public Horario updateHorario(Horario horario, Horario horarioUpdated) {
        horarioUpdated.setId(horario.getId());
        return horarioRepository.save(horarioUpdated); // Esto hará un UPDATE
    }

    @Override
    public void deleteHorario(Horario horario) {
        horarioRepository.delete(horario);
    }
}
