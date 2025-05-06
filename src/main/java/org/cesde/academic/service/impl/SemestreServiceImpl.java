package org.cesde.academic.service.impl;

import org.cesde.academic.model.Semestre;
import org.cesde.academic.repository.SemestreRepository;
import org.cesde.academic.service.ISemestreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SemestreServiceImpl implements ISemestreService {

    @Autowired
    private SemestreRepository semestreRepository;

    @Override
    public Semestre createSemestre(Semestre semestre) {
        return semestreRepository.save(semestre);
    }

    @Override
    public List<Semestre> getSemestres() {
        return semestreRepository.findAll();
    }

    @Override
    public Optional<Semestre> getSemestreById(Integer id) {
        return semestreRepository.findById(id);
    }

    @Override
    public Semestre updateSemestre(Semestre semestre, Semestre semestreUpdated) {
        semestreUpdated.setId(semestre.getId());
        return semestreRepository.save(semestreUpdated);
    }

    @Override
    public void deleteSemestre(Semestre semestre) {
        semestreRepository.delete(semestre);
    }
}
