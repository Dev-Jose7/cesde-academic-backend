package org.cesde.academic.service.impl;

import org.cesde.academic.dto.request.EscuelaRequestDTO;
import org.cesde.academic.dto.response.EscuelaResponseDTO;
import org.cesde.academic.dto.response.HorarioResponseDTO;
import org.cesde.academic.exception.RecursoExistenteException;
import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.model.Escuela;
import org.cesde.academic.model.Horario;
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
        Escuela escuela = createEntity(request, null);
        escuelaRepository.save(escuela);

        return createResponse(escuela);
    }

    @Override
    public List<EscuelaResponseDTO> getEscuelas() {
        List<Escuela> escuelas = escuelaRepository.findAll();
        return createResponseList(escuelas);
    }

    @Override
    public EscuelaResponseDTO getEscuelaById(Integer id) {
        Escuela escuela = getEscuelaByIdOrException(id);
        return createResponse(escuela);
    }

    @Override
    public List<EscuelaResponseDTO> getEscuelasByNombre(String nombre) {
        List<Escuela> escuelas = escuelaRepository.findAllByNombreContainingIgnoreCase(nombre);
        return createResponseList(escuelas);
    }

    @Override
    public EscuelaResponseDTO updateEscuela(Integer id, EscuelaRequestDTO request) {
        Escuela updatedEscuela = createEntity(request, id); // Se crea una instancia de modelo al objeto actualizado
        Escuela oldEscuela = getEscuelaByIdOrException(id); // Se obtiene registro por la clave primaria

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


    private Escuela createEntity(EscuelaRequestDTO request, Integer id){
        validateNombreUnique(request.getNombre(), id);

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

    private List<EscuelaResponseDTO> createResponseList(List<Escuela> lista) {
        List<EscuelaResponseDTO> respuesta = new ArrayList<>();
        for (Escuela escuela : lista) {
            respuesta.add(createResponse(escuela));
        }
        return respuesta;
    }
}
