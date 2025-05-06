package org.cesde.academic.service;

import org.cesde.academic.model.Programa;

import java.util.List;
import java.util.Optional;

public interface IProgramaService {

    Programa createPrograma(Programa programa);
    List<Programa> getProgramas();
    Optional<Programa> getProgramaById(Integer id);
    List<Programa> getProgramasByEscuelaId(Integer escuelaId);
    Programa updatePrograma(Programa programa, Programa programaUpdated);
    void deletePrograma(Programa programa);

    //Incluye los métodos CRUD comunes.
    //Añade getProgramasByEscuelaId, que corresponde al contrato personalizado findByEscuela_Id de su repositorio (ProgramaRepository).
}
