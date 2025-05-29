package org.cesde.academic.service.impl;

import org.cesde.academic.dto.request.PensumRequestDTO;
import org.cesde.academic.dto.response.PensumResponseDTO;
import org.cesde.academic.enums.NivelPensum;
import org.cesde.academic.exception.RecursoExistenteException;
import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.model.Modulo;
import org.cesde.academic.model.Pensum;
import org.cesde.academic.model.Programa;
import org.cesde.academic.repository.ModuloRepository;
import org.cesde.academic.repository.PensumRepository;
import org.cesde.academic.repository.ProgramaRepository;
import org.cesde.academic.service.IPensumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PensumServiceImpl implements IPensumService {

    @Autowired
    private PensumRepository pensumRepository;

    @Autowired
    private ProgramaRepository programaRepository;

    @Autowired
    private ModuloRepository moduloRepository;

    @Override
    public PensumResponseDTO createPensum(PensumRequestDTO request) {
        Pensum pensum = createEntity(request, null);
        return createResponse(pensumRepository.save(pensum));
    }

    @Override
    public List<PensumResponseDTO> getPensums() {
        return createResponseList(pensumRepository.findAll());
    }

    @Override
    public PensumResponseDTO getPensuById(Integer id) {
        return createResponse(getPensumByIdOrException(id));
    }

    @Override
    public List<PensumResponseDTO> getPensumByProgramaId(Integer id) {
        return createResponseList(pensumRepository.findAllByProgramaId(id));
    }

    @Override
    public List<PensumResponseDTO> getPensumByModuloId(Integer id) {
        return createResponseList(pensumRepository.findAllByModuloId(id));
    }

    @Override
    public List<PensumResponseDTO> getPensumByNivel(NivelPensum nivel) {
        return createResponseList(pensumRepository.findAllByNivel(nivel));
    }

    @Override
    public PensumResponseDTO updatePensum(Integer id, PensumRequestDTO request) {
        Pensum updatedPensum = createEntity(request, id);
        Pensum oldPensum = getPensumByIdOrException(id);

        updatedPensum.setId(oldPensum.getId());
        updatedPensum.setCreado(oldPensum.getCreado());

        return createResponse(pensumRepository.save(updatedPensum));
    }

    @Override
    public void deletePensum(Integer id) {
        Pensum pensum = getPensumByIdOrException(id);
        pensumRepository.delete(pensum);
    }

    // Métodos auxiliares

    private Pensum getPensumByIdOrException(Integer id){
        return pensumRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Pensum no existente"));
    }

    private Programa getProgramaByIdOrException(Integer id){
        return programaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Programa no existente"));
    }

    private Modulo getModuloByIdOrException(Integer id){
        return moduloRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Modulo no existente"));
    }

    private Pensum createEntity(PensumRequestDTO resquest, Integer id){
        validateUnique(resquest, id);

        Pensum pensum = new Pensum();
        pensum.setPrograma(getProgramaByIdOrException(resquest.getProgramaId()));
        pensum.setModulo(getModuloByIdOrException(resquest.getModuloId()));
        pensum.setNivel(resquest.getNivel());

        return pensum;
    }

    private void validateUnique(PensumRequestDTO resquest, Integer id){
        boolean exists = id == null
                ? pensumRepository.existsByProgramaIdAndModuloIdAndNivel(resquest.getProgramaId(), resquest.getModuloId(), resquest.getNivel())
                : pensumRepository.existsByProgramaIdAndModuloIdAndNivelAndIdNot(resquest.getProgramaId(), resquest.getModuloId(), resquest.getNivel(), id);

        if(exists){
            throw new RecursoExistenteException("Este pensum ya se encuentra registrado bajo estos valores");
        }
    }

    private PensumResponseDTO createResponse(Pensum pensum){
        return new PensumResponseDTO(
                pensum.getId(),
                pensum.getPrograma().getNombre(),
                pensum.getModulo().getNombre(),
                pensum.getNivel(),
                pensum.getCreado(),
                pensum.getActualizado()
        );
    }

    private List<PensumResponseDTO> createResponseList(List<Pensum> pensums){
        List<PensumResponseDTO> responseList = new ArrayList<>();
        for (Pensum pensum : pensums){
            responseList.add(createResponse(pensum));
        }
        return responseList;
    }
}
