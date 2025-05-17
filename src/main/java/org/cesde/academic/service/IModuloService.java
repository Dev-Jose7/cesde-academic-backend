package org.cesde.academic.service;

import org.cesde.academic.dto.request.ModuloRequestDTO;
import org.cesde.academic.dto.response.ModuloResponseDTO;
import org.cesde.academic.model.Modulo;

import java.util.List;
import java.util.Optional;

public interface IModuloService {

    ModuloResponseDTO createModulo(ModuloRequestDTO modulo);
    List<ModuloResponseDTO> getModulos();
    ModuloResponseDTO getModuloById(Integer id);
    List<ModuloResponseDTO> getModuloByNombre(String nombre);
    ModuloResponseDTO updateModulo(Integer id, ModuloRequestDTO request);
    void deleteModulo(Integer id);
    //Los métodos CRUD estándar.
    //El metodo específico getModulosByProgramaId que utiliza la consulta findByPrograma_Id del repositorio.
}
