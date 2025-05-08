package org.cesde.academic.service.impl;

import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.model.Dia;
import org.cesde.academic.model.FranjaHoraria;
import org.cesde.academic.model.Horario;
import org.cesde.academic.repository.DiaRepository;
import org.cesde.academic.repository.FranjaHorariaRepository;
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

    @Autowired
    private DiaRepository diaRepository;

    @Autowired
    private FranjaHorariaRepository franjaHorariaRepository;

    @Override
    public Horario createHorario(Horario horario) {
        Dia dia = diaRepository.findById(horario.getDia().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Dia no existente"));

        FranjaHoraria franjaHoraria = franjaHorariaRepository.findById(horario.getFranjaHoraria().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Franja no existente"));

        horario.setDia(dia);
        horario.setFranjaHoraria(franjaHoraria);
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
        Dia dia = diaRepository.findById(horarioUpdated.getDia().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Dia no existente"));

        FranjaHoraria franjaHoraria = franjaHorariaRepository.findById(horarioUpdated.getFranjaHoraria().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Franja no existente"));

        horarioUpdated.setId(horario.getId());
        horarioUpdated.setDia(dia);
        horarioUpdated.setFranjaHoraria(franjaHoraria);
        return horarioRepository.save(horarioUpdated); // Esto hará un UPDATE
    }

    @Override
    public void deleteHorario(Horario horario) {
        horarioRepository.delete(horario);
    }
}
