package org.cesde.academic.service;

import org.cesde.academic.model.FranjaHoraria;

import java.util.List;
import java.util.Optional;

public interface IFranjaHorariaService {

    FranjaHoraria createFranjaHoraria(FranjaHoraria franjaHoraria);
    List<FranjaHoraria> getFranjaHorarias();
    Optional<FranjaHoraria> getFranjaHorariaById(Integer id);
    FranjaHoraria updateFranjaHoraria(FranjaHoraria franjaHoraria, FranjaHoraria franjaHorariaUpdated);
    void deleteFranjaHoraria(FranjaHoraria franjaHoraria);
}
