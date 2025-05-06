package org.cesde.academic.service.impl;

import org.cesde.academic.model.Programa;
import org.cesde.academic.repository.ProgramaRepository;
import org.cesde.academic.service.IProgramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramaServiceImpl implements IProgramaService {

    @Autowired
    private ProgramaRepository programaRepository;

    @Override
    public Programa createPrograma(Programa programa) {
        return programaRepository.save(programa);
    }

    @Override
    public List<Programa> getProgramas() {
        return programaRepository.findAll();
    }

    @Override
    public Optional<Programa> getProgramaById(Integer id) {
        return programaRepository.findById(id);
    }

    @Override
    public List<Programa> getProgramasByEscuelaId(Integer escuelaId) {
        return programaRepository.findByEscuela_Id(escuelaId);
    }

    @Override
    public Programa updatePrograma(Programa programa, Programa programaUpdated) {
        programaUpdated.setId(programa.getId());
        return programaRepository.save(programaUpdated);
    }

    @Override
    public void deletePrograma(Programa programa) {
        programaRepository.delete(programa);
    }
}
