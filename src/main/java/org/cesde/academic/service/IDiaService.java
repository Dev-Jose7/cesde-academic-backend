package org.cesde.academic.service;

import org.cesde.academic.model.Dia;

import java.util.List;
import java.util.Optional;

public interface IDiaService {

    Dia createDia(Dia dia);
    List<Dia> getDias();
    Optional<Dia> getDiaById(Integer id);
    Dia updateDia(Dia dia, Dia diaUpdated);
    void deleteDia(Dia dia);
}
