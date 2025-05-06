package org.cesde.academic.service;

import org.cesde.academic.model.Clase;

import java.util.List;
import java.util.Optional;

public interface IClaseService {

    Clase createClase(Clase clase);

    List<Clase> getClases();
    Optional<Clase> getClaseById(Integer id);
    List<Clase> getClasesByGrupoId(Integer grupoId);
    List<Clase> getClasesByDocenteId(Integer docenteId);
    List<Clase> getClasesByModuloId(Integer moduloId);
    Clase updateClase(Clase clase, Clase claseUpdated);
    void deleteClase(Clase clase);
}
