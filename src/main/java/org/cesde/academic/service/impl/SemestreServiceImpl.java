package org.cesde.academic.service.impl;

import org.cesde.academic.dto.request.SemestreRequestDTO;
import org.cesde.academic.dto.response.SemestreResponseDTO;
import org.cesde.academic.exception.RecursoExistenteException;
import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.model.Semestre;
import org.cesde.academic.repository.SemestreRepository;
import org.cesde.academic.service.ISemestreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SemestreServiceImpl implements ISemestreService {

    @Autowired
    private SemestreRepository semestreRepository;

    @Override
    public SemestreResponseDTO createSemestre(SemestreRequestDTO request) {
        Semestre semestre = createEntity(request, null);
        return createResponse(semestreRepository.save(semestre));
    }

    @Override
    public List<SemestreResponseDTO> getSemestres() {
        List<Semestre> semestres = semestreRepository.findAll();
        return createResponseList(semestres);
    }

    @Override
    public SemestreResponseDTO getSemestreById(Integer id) {
        Semestre semestre = getSemestreByIdOrException(id);
        return createResponse(semestre);
    }

    @Override
    public List<SemestreResponseDTO> getSemestresByNombre(String nombre) {
        List<Semestre> semestres = semestreRepository.findAllByNombreContainingIgnoreCase(nombre);
        return createResponseList(semestres);
    }

    @Override
    public SemestreResponseDTO updateSemestre(Integer id, SemestreRequestDTO request) {
        Semestre updatedSemestre = createEntity(request, id);
        Semestre oldSemestre = getSemestreByIdOrException(id);

        updatedSemestre.setId(oldSemestre.getId());
        updatedSemestre.setCreado(oldSemestre.getCreado());

        return createResponse(semestreRepository.save(updatedSemestre));
    }

    @Override
    public void deleteSemestre(Integer id) {
        Semestre semestre = getSemestreByIdOrException(id);
        semestreRepository.delete(semestre);
    }

    // -------------------
    // Métodos auxiliares
    // -------------------

    private Semestre getSemestreByIdOrException(Integer id) {
        return semestreRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Semestre no encontrado"));
    }

    private void validateUniqueNombre(String nombre, Integer id) {
        boolean nombreExiste = id == null
                ? semestreRepository.existsByNombreIgnoreCase(nombre)
                : semestreRepository.existsByNombreIgnoreCaseAndIdNot(nombre, id);

        if (nombreExiste) {
            throw new RecursoExistenteException("Este semestre ya se encuentra registrado");
        }
    }

    private Semestre createEntity(SemestreRequestDTO request, Integer id) {
        validateUniqueNombre(request.getNombre(), id);

        Semestre semestre = new Semestre();
        semestre.setNombre(request.getNombre());
        return semestre;
    }

    private SemestreResponseDTO createResponse(Semestre semestre) {
        return new SemestreResponseDTO(
                semestre.getId(),
                semestre.getNombre(),
                semestre.getCreado(),
                semestre.getActualizado()
        );
    }

    private List<SemestreResponseDTO> createResponseList(List<Semestre> semestres) {
        List<SemestreResponseDTO> responseList = new ArrayList<>();
        for (Semestre semestre : semestres) {
            responseList.add(createResponse(semestre));
        }
        return responseList;
    }
}
