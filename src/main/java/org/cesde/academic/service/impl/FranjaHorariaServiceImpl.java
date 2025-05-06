package org.cesde.academic.service.impl;

import org.cesde.academic.model.FranjaHoraria;
import org.cesde.academic.repository.FranjaHorariaRepository;
import org.cesde.academic.service.IFranjaHorariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FranjaHorariaServiceImpl implements IFranjaHorariaService {

    @Autowired
    private FranjaHorariaRepository franjaHorariaRepository;

    @Override
    public FranjaHoraria createFranjaHoraria(FranjaHoraria franjaHoraria) {
        return franjaHorariaRepository.save(franjaHoraria);
    }

    @Override
    public List<FranjaHoraria> getFranjaHorarias() {
        return franjaHorariaRepository.findAll();
    }

    @Override
    public Optional<FranjaHoraria> getFranjaHorariaById(Integer id) {
        return franjaHorariaRepository.findById(id);
    }

    @Override
    public FranjaHoraria updateFranjaHoraria(FranjaHoraria franjaHoraria, FranjaHoraria franjaHorariaUpdated) {
        franjaHorariaUpdated.setId(franjaHoraria.getId());
        return franjaHorariaRepository.save(franjaHorariaUpdated); // Esto hará un UPDATE
    }

    @Override
    public void deleteFranjaHoraria(FranjaHoraria franjaHoraria) {
        franjaHorariaRepository.delete(franjaHoraria);
    }
}
