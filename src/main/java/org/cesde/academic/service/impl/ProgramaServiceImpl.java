package org.cesde.academic.service.impl;

import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.model.Escuela;
import org.cesde.academic.model.Programa;
import org.cesde.academic.repository.EscuelaRepository;
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

    @Autowired
    private EscuelaRepository escuelaRepository;

    @Override
    public Programa createPrograma(Programa programa) {
        Escuela escuela = escuelaRepository.findById(programa.getEscuela().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Escuela no existente"));

        programa.setEscuela(escuela);
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
    public Optional<Programa> getProgramaByNombre(String nombre) {
        return programaRepository.findByNombre(nombre);
    }

    @Override
    public List<Programa> getProgramasByEscuelaId(Integer escuelaId) {
        return programaRepository.findByEscuela_Id(escuelaId);
    }

    @Override
    public Programa updatePrograma(Programa programa, Programa programaUpdated) {
        Escuela escuela = escuelaRepository.findById(programaUpdated.getEscuela().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Escuela no existente"));

        programaUpdated.setId(programa.getId());
        programaUpdated.setEscuela(escuela);
        return programaRepository.save(programaUpdated);
    }

    @Override
    public void deletePrograma(Programa programa) {
        programaRepository.delete(programa);
    }
}
