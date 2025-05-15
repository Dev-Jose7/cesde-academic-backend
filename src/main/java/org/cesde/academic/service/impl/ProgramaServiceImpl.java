package org.cesde.academic.service.impl;

import org.cesde.academic.dto.request.ProgramaRequestDTO;
import org.cesde.academic.dto.response.ProgramaResponseDTO;
import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.model.Escuela;
import org.cesde.academic.model.Programa;
import org.cesde.academic.repository.EscuelaRepository;
import org.cesde.academic.repository.ProgramaRepository;
import org.cesde.academic.service.IProgramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProgramaServiceImpl implements IProgramaService {

    @Autowired
    private ProgramaRepository programaRepository;

    @Autowired
    private EscuelaRepository escuelaRepository;

    @Override
    public ProgramaResponseDTO createPrograma(ProgramaRequestDTO request) {
        Programa programa = createEntity(request, null);
        return createResponse(programaRepository.save(programa));
    }

    @Override
    public List<ProgramaResponseDTO> getProgramas() {
        List<Programa> programas = programaRepository.findAll();
        return createResponseList(programas);
    }

    @Override
    public ProgramaResponseDTO getProgramaById(Integer id) {
        Programa programa = getProgramaByIdOrException(id);
        return createResponse(programa);
    }

    @Override
    public List<ProgramaResponseDTO> getProgramasByNombre(String nombre) {
        List<Programa> programas = programaRepository.findAllByNombreContainingIgnoreCase(nombre);
        return createResponseList(programas);
    }

    @Override
    public List<ProgramaResponseDTO> getProgramasByEscuelaId(Integer escuelaId) {
        List<Programa> programas = programaRepository.findByEscuela_Id(escuelaId);
        return createResponseList(programas);
    }

    @Override
    public ProgramaResponseDTO updatePrograma(Integer id, ProgramaRequestDTO request) {
        Programa updatedPrograma = createEntity(request, id);
        Programa oldPrograma = getProgramaByIdOrException(id);

        updatedPrograma.setId(oldPrograma.getId());
        updatedPrograma.setCreado(oldPrograma.getCreado());

        return createResponse(programaRepository.save(updatedPrograma));
    }

    @Override
    public void deletePrograma(Integer id) {
        Programa programa = getProgramaByIdOrException(id);
        programaRepository.delete(programa);
    }

    // Metodos auxiliares
    private Programa getProgramaByIdOrException(Integer id){
        return programaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Programa no existente"));
    }

    private Escuela getEscuelaByIdOrException(Integer id){
        return escuelaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Escuela no existente"));
    }

    private void validateUniqueNombre(String nombre, Integer id){
        boolean nombreExiste = id == null
                ? programaRepository.existsByNombreIgnoreCase(nombre)
                : programaRepository.existsByNombreIgnoreCaseAndIdNot(nombre, id);

        if (nombreExiste){
            throw new RecursoNoEncontradoException("Este programa ya se encuentra registrado");
        }
    }

    private Programa createEntity(ProgramaRequestDTO request, Integer id){
        validateUniqueNombre(request.getNombre(), id);

        Programa programa = new Programa();
        programa.setEscuela(getEscuelaByIdOrException(request.getEscuelaId()));
        programa.setNombre(request.getNombre());
        return programa;
    }

    private ProgramaResponseDTO createResponse(Programa programa){
        return new ProgramaResponseDTO(
                programa.getId(),
                programa.getEscuela().getNombre(),
                programa.getNombre(),
                programa.getCreado(),
                programa.getActualizado()
        );
    }

    private List<ProgramaResponseDTO> createResponseList(List<Programa> programas){
        List<ProgramaResponseDTO> programasResponse = new ArrayList<>();
        for (Programa programa : programas){
            programasResponse.add(createResponse(programa));
        }
        return programasResponse;
    }
}
