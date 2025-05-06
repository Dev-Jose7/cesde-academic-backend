package org.cesde.academic.service;

import org.cesde.academic.model.Escuela;

import java.util.List;
import java.util.Optional;

public interface IEscuelaService {

    Escuela createEscuela(Escuela escuela);
    List<Escuela> getEscuelas();
    Optional<Escuela> getEscuelaById(Integer id);
    Optional<Escuela> getEscuelaByNombre(String nombre);
    Escuela updateEscuela(Escuela escuela, Escuela escuelaUpdated);
    void deleteEscuela(Escuela escuela);
}
