package org.cesde.academic.service.impl;

import org.cesde.academic.model.Dia;
import org.cesde.academic.repository.DiaRepository;
import org.cesde.academic.service.IDiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiaServiceImpl implements IDiaService {

    @Autowired
    private DiaRepository diaRepository;

    @Override
    public Dia createDia(Dia dia) {
        return diaRepository.save(dia);
    }

    @Override
    public List<Dia> getDias() {
        return diaRepository.findAll();
    }

    @Override
    public Optional<Dia> getDiaById(Integer id) {
        return diaRepository.findById(id);
    }

    @Override
    public Dia updateDia(Dia dia, Dia diaUpdated) {
        diaUpdated.setId(dia.getId());
        return diaRepository.save(diaUpdated);
    }

    @Override
    public void deleteDia(Dia dia) {
        diaRepository.delete(dia);
    }
}
