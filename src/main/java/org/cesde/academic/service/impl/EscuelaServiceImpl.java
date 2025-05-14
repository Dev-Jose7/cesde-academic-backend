package org.cesde.academic.service.impl;

import org.cesde.academic.dto.request.EscuelaRequestDTO;
import org.cesde.academic.dto.response.EscuelaResponseDTO;
import org.cesde.academic.exception.RecursoExistenteException;
import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.model.Escuela;
import org.cesde.academic.repository.EscuelaRepository;
import org.cesde.academic.service.IEscuelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EscuelaServiceImpl implements IEscuelaService {

    @Autowired
    EscuelaRepository escuelaRepository;

    @Override
    public EscuelaResponseDTO createEscuela(EscuelaRequestDTO request) {
        validateNombreUnique(request.getNombre(), null);
        Escuela escuela = createEntity(request);
        escuelaRepository.save(escuela);

        return createResponse(escuela);
    }

    @Override
    public List<EscuelaResponseDTO> getEscuelas() {
        List<Escuela> escuelaList = escuelaRepository.findAll();
        List<EscuelaResponseDTO> escuelaResponseDTOList = new ArrayList<>();

        for (Escuela escuela : escuelaList){
            escuelaResponseDTOList.add(createResponse(escuela));
        }
        return escuelaResponseDTOList;
    }

    @Override
    public EscuelaResponseDTO getEscuelaById(Integer id) {
        Escuela escuela = getEscuelaByIdOrException(id);
        return createResponse(escuela);
    }

    @Override
    public List<EscuelaResponseDTO> getEscuelaByNombre(String nombre) {
        List<Escuela> escuelas = escuelaRepository.findAllByNombreContainingIgnoreCase(nombre);
        List<EscuelaResponseDTO> escuelaList = new ArrayList<>();

        for (Escuela escuela : escuelas){
            escuelaList.add(createResponse(escuela));
        }

        return escuelaList;
    }

    @Override
    public EscuelaResponseDTO updateEscuela(Integer id, EscuelaRequestDTO request) {
        validateNombreUnique(request.getNombre(), id);
        Escuela oldEscuela = getEscuelaByIdOrException(id); // Se obtiene registro por la clave primaria

        Escuela updatedEscuela = createEntity(request); // Se crea una instancia de modelo al objeto actualizado
        updatedEscuela.setId(oldEscuela.getId());
        updatedEscuela.setCreado(oldEscuela.getCreado());

        return createResponse(escuelaRepository.save(updatedEscuela));
    }

    @Override
    public void deleteEscuela(Integer id) {
        Escuela escuela = getEscuelaByIdOrException(id);
        escuelaRepository.delete(escuela);
    }

     // Métodos auxiliares
    private Escuela getEscuelaByIdOrException(Integer id){
        return escuelaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Escuela no existente"));
    }

    private void validateNombreUnique(String nombre, Integer id) {
        boolean nombreExiste = id == null
                ? escuelaRepository.existsByNombreIgnoreCase(nombre)
                : escuelaRepository.existsByNombreIgnoreCaseAndIdNot(nombre, id);

        if (nombreExiste) {
            throw new RecursoExistenteException("Esta escuela ya se encuentra registrada");
        }
    }


    private Escuela createEntity(EscuelaRequestDTO request){
        Escuela escuela = new Escuela();
        escuela.setNombre(request.getNombre());
        return escuela;
    }

    private EscuelaResponseDTO createResponse(Escuela escuela){
        return new EscuelaResponseDTO(
                escuela.getId(),
                escuela.getNombre(),
                escuela.getCreado(),
                escuela.getActualizado());
    }
}
