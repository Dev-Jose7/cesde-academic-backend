package org.cesde.academic.service;

import org.cesde.academic.dto.request.EscuelaRequestDTO;
import org.cesde.academic.dto.response.EscuelaResponseDTO;

import java.util.List;

public interface IEscuelaService {

    EscuelaResponseDTO createEscuela(EscuelaRequestDTO escuela);
    List<EscuelaResponseDTO> getEscuelas();
    EscuelaResponseDTO getEscuelaById(Integer id);
    List<EscuelaResponseDTO> getEscuelasByNombre(String nombre);
    EscuelaResponseDTO updateEscuela(Integer id, EscuelaRequestDTO updatedEscuela);
    void deleteEscuela(Integer id);

//    Escuela createEscuela(Escuela escuela);
//    List<Escuela> getEscuelas();
//    Optional<Escuela> getEscuelaById(Integer id);
//    Optional<Escuela> getEscuelaByNombre(String nombre);
//    Escuela updateEscuela(Escuela escuela, Escuela escuelaUpdated);
//    void deleteEscuela(Escuela escuela);
}
