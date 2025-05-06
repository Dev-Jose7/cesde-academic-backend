package org.cesde.academic.service.impl;

import org.cesde.academic.model.Escuela;
import org.cesde.academic.repository.EscuelaRepository;
import org.cesde.academic.service.IEscuelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EscuelaServiceImpl implements IEscuelaService {

    @Autowired
    EscuelaRepository escuelaRepository;

    @Override
    public Escuela createEscuela(Escuela escuela) {
        return escuelaRepository.save(escuela);
    }

    @Override
    public List<Escuela> getEscuelas() {
        return escuelaRepository.findAll();
    }

    @Override
    public Optional<Escuela> getEscuelaById(Integer id) {
        return escuelaRepository.findById(id);
    }

    @Override
    public Optional<Escuela> getEscuelaByNombre(String nombre) {
        return escuelaRepository.findByNombre(nombre);
    }

    @Override
    public Escuela updateEscuela(Escuela escuela, Escuela escuelaUpdated) {
        escuelaUpdated.setId(escuela.getId());
        return escuelaRepository.save(escuelaUpdated);
    }

    @Override
    public void deleteEscuela(Escuela escuela) {
        escuelaRepository.delete(escuela);
    }
}
