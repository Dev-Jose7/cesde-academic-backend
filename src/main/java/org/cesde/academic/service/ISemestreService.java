package org.cesde.academic.service;

import org.cesde.academic.model.Semestre;

import java.util.List;
import java.util.Optional;

public interface ISemestreService {

    Semestre createSemestre(Semestre semestre);
    List<Semestre> getSemestres();
    Optional<Semestre> getSemestreById(Integer id);
    Semestre updateSemestre(Semestre semestre, Semestre semestreUpdated);
    void deleteSemestre(Semestre semestre);
}
