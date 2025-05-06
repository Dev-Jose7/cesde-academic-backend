package org.cesde.academic.service;

import org.cesde.academic.model.Modulo;

import java.util.List;
import java.util.Optional;

public interface IModuloService {

    Modulo createModulo(Modulo modulo);
    List<Modulo> getModulos();
    Optional<Modulo> getModuloById(Integer id);
    List<Modulo> getModulosByProgramaId(Integer programaId);
    Modulo updateModulo(Modulo modulo, Modulo moduloUpdated);
    void deleteModulo(Modulo modulo);
    //Los métodos CRUD estándar.
    //El metodo específico getModulosByProgramaId que utiliza la consulta findByPrograma_Id del repositorio.
}
